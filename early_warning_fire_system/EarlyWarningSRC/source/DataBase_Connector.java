package source;

/**
 *
 * @author Ethan Rae
 */
import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JLabel;
import org.apache.derby.drda.NetworkServerControl;
import static source.Main.NUM_OF_SENSORS;
import static source.Main.main_view;

public final class DataBase_Connector {

    //boolean that will create a new table every time the program is restarted
    private static boolean table_created = false;

    //the server that will hold our database
    private NetworkServerControl server;

    //Connection associated with this DataBase_Connector
    private Connection conn;

    public DataBase_Connector() {

    }

    //Deletes Table if one exists already
    public void deleteTable() {
        Statement sta;
        try {
            sta = conn.createStatement();

            //Deletes the table
            int wasTableDeleted = sta.executeUpdate("DROP TABLE SENSORS");

            if (wasTableDeleted >= 0) {
                System.out.println("Table deleted.");
            }

        } catch (SQLException ex) {
            //No Table to Delete, Do nothing
        }
        table_created = false;
    }

    //Creates Table
    public void createTable() {
        //delete table if one already exists
        //deleteTable();
        try {
            try (Statement sta = conn.createStatement()) {
                int wasTableCreated = sta.executeUpdate("CREATE TABLE SENSORS(\"TIME\" DOUBLE,\"SENSORID\" INTEGER not null primary key,\"TEMP\" DOUBLE,\"HUM\" DOUBLE,\"LIGHT\" DOUBLE,\"VOLTAGE\" DOUBLE)");

                if (wasTableCreated >= 0) {
                    System.out.println("Table created.");
                }
            }
            table_created = true;
        } catch (SQLException ex) {
            //table wasn't created 
            //or Statement sta could not be closed, (shouldn't happen)
            //table_created will stay equal to false
            //ex.printStackTrace();
        }
    }

    private boolean createConnection() {
        try {

            String dName = "student";
            String dPass = "student";
            String host = "jdbc:derby://localhost:1527/SensorDB;create=true";

            conn = DriverManager.getConnection(host, dName, dPass);

        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
        if (table_created == false) {
            createTable();
        }
        return true;
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void printDebugDB() {
        createConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from STUDENT.SENSORS");

            StringBuffer sb = new StringBuffer("");
            while (rs.next()) {

                sb.append("TIME=").append(rs.getDouble(1)).append("\t");
                sb.append("SENSORID=").append(rs.getInt(2)).append("\t");
                sb.append("TEMP=").append(rs.getDouble(3)).append("\t");
                sb.append("HUM=").append(rs.getDouble(4)).append("\t");
                sb.append("LIGHT=").append(rs.getDouble(5)).append("\t");
                sb.append("VOLTAGE=").append(rs.getDouble(6)).append("\n");

            }
            System.out.println("\n" + sb);
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateDatabase(String table_name, String file_name) {
        createConnection();
        createTable();

        try {
            PreparedStatement ps = conn.prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE "
                    + "(null, '" + table_name + "', '" + file_name + "', null, null, null,1)");

            int result = ps.executeUpdate();

            if (result >= 0) {
                System.out.println("Database Updated");
            }

        } catch (SQLException e) {
            //System.err.println("updateDatabase() Exception caught");
            //e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void insertData(Sensor S) {
        double time = S.getTime();
        int id = S.getId();
        double temp = S.getTemp();
        double hum = S.getHum();
        double light = S.getLight();
        double voltage = S.getVoltage();
        createConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert at STUDENT.SENSORS values(?,?,?,?,?,?)");
            ps.setDouble(1, time);
            ps.setInt(2, id);
            ps.setDouble(3, temp);
            ps.setDouble(4, hum);
            ps.setDouble(5, light);
            ps.setDouble(6, voltage);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Updated/Added Id: " + id + " in database");
                System.out.println("Data Inserted");
            } else {
                System.out.println("Something Happend");
            }
        } catch (SQLException e) {

        } finally {
            closeConnection();
        }
    }

    /**
     * Starts the data base server
     */
    public void startDBServer() {
        try {
            System.setProperty("derby.drda.startNetworkServer", "true");
            // start derby in port 1527
            // db user - student
            // db user password - student
            server = new NetworkServerControl(InetAddress.getByName("localhost"),
                    1527,
                    "student",
                    "student");
            server.start(null);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    /**
     * Shuts Down the data base server
     */
    public void shutdownDBServer() {
        try {
            server.shutdown();
        } catch (Exception ex) {
            //ex.printStackTrace();
            //Server wasn't started
        }
    }

    public boolean isServerRunning() {
        try (Socket s = new Socket("localhost", 1527)) {
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }

    @Override
    public String toString() {
        return "Data Base Connector";
    }

    public Vector getSensorFromDB() {
        createConnection();
        Vector sensors = new Vector(NUM_OF_SENSORS);
        Double total_temp_avg = 0.0;
        Double total_lum_avg = 0.0;
        Double total_hum_avg = 0.0;
        int num_with_low_voltage = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from STUDENT.SENSORS");
            double time;
            int id;
            double temp;
            double hum;
            double light;
            double voltage;

            for (int i = 0; i < NUM_OF_SENSORS && rs.next(); i++) {
                time = rs.getDouble(1);
                id = rs.getInt(2);
                temp = rs.getDouble(3);
                hum = rs.getDouble(4);
                light = rs.getDouble(5);
                voltage = rs.getDouble(6);

                //Fault Tolerance
                if (voltage >= Update_Sensors_TimerTask.MIN_SENSOR_VOLTAGE) {
                    total_temp_avg += temp;
                    total_lum_avg += light;
                    total_hum_avg += hum;
                } else {
                    num_with_low_voltage++;
                }
                sensors.add(new Sensor(time, id, temp, hum, light, voltage));
            }
            total_temp_avg = (total_temp_avg / Main.NUM_OF_SENSORS - num_with_low_voltage);

            if (main_view != null) {
                JLabel avg_Temp_Img = main_view.getAvg_Temp_Img();
                JLabel avg_Temp_Text = main_view.getAvg_Temp_Text();
                if (total_temp_avg <= 20) {
                    avg_Temp_Img.setBackground(Color.green);
                } else if (total_temp_avg <= 30) {
                    avg_Temp_Img.setBackground(Color.yellow);
                } else if (total_temp_avg <= 50) {
                    avg_Temp_Img.setBackground(new Color(205,132,6));
                } else {
                    avg_Temp_Img.setBackground(Color.red);
                }
                Table_Model table_model = main_view.getTable_model();
                if (Table_Model.showing_all) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    avg_Temp_Text.setText("\tAverage Temperature: " + df.format(total_temp_avg) + "°C");
                }
                main_view.setTotal_temp_avg(total_temp_avg);
                main_view.setTotal_hum_avg(total_hum_avg);
                main_view.setTotal_lum_avg(total_lum_avg);
            }
            //System.out.println("Total Avg Temp: " + Main.total_temp_avg);
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sensors;
    }

}

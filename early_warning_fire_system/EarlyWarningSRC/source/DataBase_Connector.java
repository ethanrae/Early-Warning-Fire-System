package source;

/**
 *
 * @author Ethan Rae
 */
import java.awt.Color;
import java.net.InetAddress;
import java.sql.*;
import org.apache.derby.drda.NetworkServerControl;
import static source.View.Avg_Temp_Img;
import static source.View.Avg_Temp_Text;

public final class DataBase_Connector {

    //boolean that will create a new table every time the program is restarted
    private static boolean table_created = false;

    //the server that will hold our database
    private NetworkServerControl server;

    //Connection associated with this DataBase_Connector
    private Connection con;

    public DataBase_Connector() {

    }

    //Deletes Table if one exists already
    public void deleteTable() {
        Statement sta;
        try {
            sta = con.createStatement();

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
            try (Statement sta = con.createStatement()) {
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
            //Properties prop = new Properties();

            // the configuration file name
            //String fileName = "app.config";
            //InputStream is;
            //is = new FileInputStream(fileName);
            // load the properties file
            //prop.load(is);
            // get the value for app.name key
            //System.out.println(prop.getProperty("user.name"));
            //String dName = prop.getProperty("user.name");
            String dName = "student";
            // get the value for app.version key
            //System.out.println(prop.getProperty("user.password"));
            //String dPass = prop.getProperty("user.password");
            String dPass = "student";
            //String host = "jdbc:derby://localhost:1527/CustomerMileageDB";
            String host = "jdbc:derby://localhost:1527/SensorDB;create=true";

            con = DriverManager.getConnection(host, dName, dPass);

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
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void viewData() {
        createConnection();

        try {
            Statement st = con.createStatement();
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

    //Might use this code later for cell queries
    /*
     public long getMileage(int key) {
     createConnection();
     long mileage = 0;
     try {
     Statement st = con.createStatement();
     ResultSet rs = st.executeQuery("SELECT \"MILEAGE\" FROM STUDENT.CUSTOMERS_TABLE where ID=" + key);

     while (rs.next()) {
     mileage = rs.getLong("MILEAGE");
     }

     } catch (SQLException e) {
     //e.printStackTrace();
     }
     return mileage;
     }*/
    public void updateDatabase(String table_name, String file_name) {
        createConnection();
        createTable();

        try {
            PreparedStatement ps = con.prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE "
                    + "(null, '" + table_name + "', '" + file_name + "', null, null, null,1)");

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Data Updated");
            } else {
                //System.err.println("Something Happend");
            }
        } catch (SQLException e) {
            System.err.println("updateDatabase() Exception caught");
            e.printStackTrace();
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
            PreparedStatement ps = con.prepareStatement("insert into STUDENT.SENSORS values(?,?,?,?,?,?)");
            ps.setDouble(1, time);
            ps.setInt(2, id);
            ps.setDouble(3, temp);
            ps.setDouble(4, hum);
            ps.setDouble(5, light);
            ps.setDouble(6, voltage);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Data Inserted");
            } else {
                System.out.println("Something Happend");
            }
        } catch (SQLException e) {
            //Trying to add an id that already exists
        } finally {
            closeConnection();
        }
    }

    /**
     * Starts the data base server
     */
    public void startDataBaseServer() {
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
    public void shutdownDataBaseServer() {
        try {
            server.shutdown();
        } catch (Exception ex) {
            //ex.printStackTrace();
            //Server wasn't started
        }
    }

    @Override
    public String toString() {
        return "Data Base Connector";
    }

    public Object[][] getSensors() {
        createConnection();
        Object[][] sensors = new Object[1024][6];
        Main.total_temp_avg = 0.0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from STUDENT.SENSORS");
            double time;
            int id;
            double temp;
            double hum;
            double light;
            double voltage;

            for (int i = 0; i < sensors.length && rs.next(); i++) {
                time = rs.getDouble(1);
                id = rs.getInt(2);
                temp = rs.getDouble(3);
                Main.total_temp_avg += temp;
                hum = rs.getDouble(4);
                light = rs.getDouble(5);
                voltage = rs.getDouble(6);
                sensors[i][0] = time;
                sensors[i][1] = id;
                sensors[i][2] = temp;
                sensors[i][3] = hum;
                sensors[i][4] = light;
                sensors[i][5] = voltage;
            }
            Main.total_temp_avg = Main.total_temp_avg / Main.NUM_OF_SENSORS;

            if (Avg_Temp_Img != null) {
                if (Main.total_temp_avg <= 20) {
                    Avg_Temp_Img.setBackground(Color.green);
                } else if (Main.total_temp_avg <= 30) {
                    Avg_Temp_Img.setBackground(Color.yellow);
                } else if (Main.total_temp_avg <= 40) {
                    Avg_Temp_Img.setBackground(Color.orange);
                } else {
                    Avg_Temp_Img.setBackground(Color.red);
                }
                Avg_Temp_Text.setText("\tAverage Temperature: " + Main.total_temp_avg);
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

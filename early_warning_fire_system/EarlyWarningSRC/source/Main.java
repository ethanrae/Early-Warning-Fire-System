package source;

import java.awt.Color;
import static java.lang.Thread.sleep;
import static source.View.updateGridCellColors;
import static source.View.table_model;

/**
 *
 * @author ethan.rae045
 */
public class Main {

    public static double total_temp_avg = 0.0;
    public static final int NUM_OF_SENSORS = 1024;
    public static View window;
    public static Thread ViewThread;
    public static Thread DataUpdateThread;
    public static boolean start_debug = false; //true = print current database, false = run program normally
    public static boolean thread = false; //TODO use is_database_started to control when to start ViewThread
    public static String table_name = "SENSORS"; //The name of the data base table used in the program
    public static String[] sensor_filenames = {"time0.0_Sensors.txt", "time0.5_Sensors.txt", "time1.0_Sensors.txt", "time1.5_Sensors.txt", "time2.0_Sensors.txt", "time2.5_Sensors.txt", "time3.0_Sensors.txt", "time3.5_Sensors.txt", "time4.0_Sensors.txt", "time4.5_Sensors.txt", "time5.0_Sensors.txt"}; //The time incremented data text files for 1024 sensors each
    public static DataBase_Connector db_helper; //Creating global static database helper to avoid repeated 'new' calls
    public static Controller listener = new Controller();
    public static final Color RED = Color.red;
    public static final Color YELLOW = Color.yellow;
    public static final Color GREEN = Color.green;
    public static final String appName = "Early Warning Fire System";
    /*
     The main program of the early warning fire system
     */

    public static void main(String[] args) {
        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", appName);
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            
        }
        //Start database server programmatically
        db_helper = new DataBase_Connector();
        db_helper.startDataBaseServer();
        try {
            sleep(5000);
        } catch (InterruptedException ex) {

        }
        //Get current data from existing database
        Object[][] db_sensors = db_helper.getSensors();

        //Add our new sensor_model to a new Table_Model and assign it to our table_model
        table_model = new Table_Model(db_sensors);

        if (start_debug == true) //Print current data base
        {
            System.out.println("Debug Started");
            db_helper.viewData();
            db_helper.shutdownDataBaseServer();
            System.out.println("Debug Finished");
        } else //Start program normally
        {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new View().setVisible(true);
                }
            });

            //ViewThread = new Thread(new ViewUpdate());
            DataUpdateThread = new Thread(new DataUpdate());

            //ViewThread.start();
            DataUpdateThread.start();

        }

    }

    /*
     Thread that runs the GUI window view // currently not used 4/14/14
     */
    public static class ViewUpdate implements Runnable {

        @Override
        public void run() {
            window = new View();
            window.setVisible(true);
        }

    }

    /*
     Thread for updating the database every 30 seconds
     Runs as long as the program is running
     */
    public static class DataUpdate implements Runnable {

        @Override
        public void run() {
            while (true) {

                for (String sensor_filename : sensor_filenames) {
                    try {
                        //update the data base with new data
                        db_helper.updateDatabase(table_name, sensor_filename);

                        //update table model with new data from database
                        table_model.all_sensors = db_helper.getSensors();

                        updateGridCellColors();

                        //notify the table to update its view
                        table_model.fireTableDataChanged();

                        //sleep 30 seconds
                        Thread.sleep(10000);//10s

                    } catch (Exception ex) {
                    }
                }
            }
        }
    }
}

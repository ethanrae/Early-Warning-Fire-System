
package source;

import javax.swing.JLabel;
import javax.swing.JPanel;
import static source.View.table_model;

/**
 *
 * @author ethan.rae045
 */

public class Main {
    
    public static double total_temp_avg = 0.0;
    public static final int NUM_OF_SENSORS = 1024;
    public static boolean start_debug = false; //true = print current database, false = run program normally
    private static boolean is_database_started = false; //TODO use is_database_started to control when to start ViewThread
    public static String table_name = "SENSORS"; //The name of the data base table used in the program
    public static String[] sensor_filenames = {"time0.0_Sensors.txt","time0.5_Sensors.txt","time1.0_Sensors.txt","time1.5_Sensors.txt","time2.0_Sensors.txt","time2.5_Sensors.txt","time3.0_Sensors.txt","time3.5_Sensors.txt","time4.0_Sensors.txt","time4.5_Sensors.txt","time5.0_Sensors.txt"}; //The time incremented data text files for 1024 sensors each
    public static DataBase_Connector db_helper = new DataBase_Connector(); //Creating global static database helper to avoid repeated 'new' calls
    
    /*
    The main program of the early warning fire system
    */
    public static void main(String[] args) {
        //Start database server programmatically
        db_helper.startDataBaseServer();
        
        //Get current data from existing database
        Object[][] db_sensors = db_helper.getSensors();
        
        //Put the retrived sensor data into our sensor_model
        Sensors_Model sensor_model = new Sensors_Model(db_sensors);
        
        //Add our new sensor_model to a new Table_Model and assign it to our table_model
        table_model = new Table_Model(sensor_model);
        
        
        if(start_debug == true) //Print current data base
        {
            System.out.println("Debug Started");
            db_helper.viewData();
            db_helper.shutdownDataBaseServer();
            System.out.println("Debug Finished");
        }
        else //Start program normally
        {
            new Thread(new DataUpdateThread()).start();

            new Thread(new ViewThread()).start();   
        }
        
    }
    
    /*
    Thread that runs the GUI window view
    */
    public static class ViewThread implements Runnable
    {
        @Override
        public void run()
        {
            View window = new View();
            window.setVisible(true);
        }
    }
    
    /*
    Thread for updating the database every 30 seconds
    Runs as long as the program is running
    */
    public static class DataUpdateThread implements Runnable
    {
        @Override
        public void run()
        {
            while(true)
            {

                for (String sensor_filename : sensor_filenames) {
                    try {
                        //update the data base with new data
                        db_helper.updateDatabase(table_name, sensor_filename); 
                        
                        //update table model with new data from database
                        table_model.all_sensors.setSensors(db_helper.getSensors()); 
                        

                        View.updateCellColors();
                        
                        //notify the table to update its view
                        table_model.fireTableDataChanged(); 
                        
                        //sleep 30 seconds
                        Thread.sleep(5000);//5s
                    }catch (InterruptedException ex) {}
                }
            }
        }
    }
}

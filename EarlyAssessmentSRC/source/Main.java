
package source;

import java.util.logging.Level;
import java.util.logging.Logger;
import static source.View.table_model;

/**
 *
 * @author ethan.rae045
 */

public class Main {
    
    public static boolean start_debug = false;
    private static boolean is_database_started = false;
    public static String table_name = "SENSORS";
    public static String[] sensor_filenames = {"time0.0_Sensors.txt","time0.5_Sensors.txt"};
    public static DataBase_Connector db_helper = new DataBase_Connector();
    public static void main(String[] args) {
        db_helper.startDataBaseServer();
        Object[][] db_sensors = db_helper.getSensors();
        Sensors_Model sensor_model = new Sensors_Model(db_sensors);
        table_model = new Table_Model(sensor_model);
        if(start_debug)
        {
            System.out.println("Debug Started");
            db_helper.viewData();
            db_helper.shutdownDataBaseServer();
            System.out.println("Debug Finished");
        }
        else
        {
            new Thread(new DataUpdateThread()).start();

            new Thread(new ViewThread()).start();   
        }
        
    }
    public static class ViewThread implements Runnable
    {
        @Override
        public void run()
        {
            View window = new View();
            window.setVisible(true);
        }
    }
    
    public static class DataUpdateThread implements Runnable
    {
        @Override
        public void run()
        {
            while(true)
            {

                for (String sensor_filename : sensor_filenames) {
                    try {
                        db_helper.updateDatabase(table_name, sensor_filename);
                        table_model.all_sensors.setSensors(db_helper.getSensors());
                        table_model.fireTableDataChanged();
                        Thread.sleep(30000);//sleep 30 seconds
                    }catch (InterruptedException ex) {
                        
                    }
                }
            }
        }
    }
}

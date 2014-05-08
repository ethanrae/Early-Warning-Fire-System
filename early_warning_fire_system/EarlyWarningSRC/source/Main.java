package source;

import static java.lang.Thread.sleep;
import java.util.Timer;

/**
 *
 * @author ethan.rae045
 */
public class Main {

    public static View main_view;
    public static final int TABLE_SIZE = 32;
    public static int NUM_OF_SENSORS = 1024;
    public static final boolean START_DEBUG = false; //true = print current database, false = run program normally
    public static final DataBase_Connector db_helper = new DataBase_Connector(); //Creating global static database helper to avoid repeated 'new' calls
    public static final Controller listener = new Controller();
    public static final Timer data_stream_timer = new Timer();

    /*
     The main program of the early warning fire system
     */
    public static void main(String[] args) {

        //Start database server programmatically
        db_helper.startDBServer();

        waitForServerToStart();

        if (START_DEBUG == true) //Print current data base
        {
            System.out.println("Debug Started");
            db_helper.printDebugDB();
            db_helper.shutdownDBServer();
            System.out.println("Debug Finished");
        } else //Start program normally
        {
            //If using Mac OS, makes menubar of our GUI appear in the Mac OS toolbar
            //instead of being part of the GUI frame
            if (System.getProperty("os.name").contains("Mac")) {
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Early Warning Fire System");
                System.setProperty("apple.laf.useScreenMenuBar", "true");

            }

            //Start TimerTask Thread for simulating a stream of data sensors
            Update_Sensors_TimerTask task = new Update_Sensors_TimerTask();
            final int seconds = 30;
            data_stream_timer.scheduleAtFixedRate(task, 3000, seconds * 1000);

            //Start Thread to run GUI created with Netbeans GUI Builder
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    main_view = new View();
                    main_view.setVisible(true);
                }
            });
        }
    }

    private static void waitForServerToStart() {
        while (!db_helper.isServerRunning()) {
            System.out.println("Server Not Started");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
        System.out.println("Server Started");
    }

}

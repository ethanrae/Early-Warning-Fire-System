
package source;

/**
 *
 * @author ethan.rae045
 */

public class Main {
    
    public static boolean start_debug = false;
    
    public static void main(String[] args) {
        
        if(start_debug)
        {
            DataBase_Connector db_helper = new DataBase_Connector();
            db_helper.startDataBaseServer();
            System.out.println("Debug Started");
            db_helper.viewData();
            db_helper.shutdownDataBaseServer();
            System.out.println("Debug Finished");
        }
        else
        {
            new Thread(new FrameRunnable()).start();
        }
        
    }
    public static class FrameRunnable implements Runnable
    {
        @Override
        public void run()
        {
            View window = new View();
            window.setVisible(true);
        }
    }
}

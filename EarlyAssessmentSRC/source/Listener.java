package source;

/**
 *
 * @author ethan.rae045
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Listener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //System.out.println(e.getActionCommand());
        
        //Should use switch statement on String Object but only two buttons for now
        if(e.getActionCommand().equalsIgnoreCase("Close"))
        {
            System.exit(0);
        }
        else if (e.getActionCommand().equalsIgnoreCase("Generate Data")) //could be else
        {
            //Does nothing as of now
            //Need to reconfigure the methods and organization
            String columnNames[] = { "Alert","ID", "Temp", "Humidity", "Battery Life", "Location" };
	
            //Create 1024 stub Sensor objects with random data variables
            Window.createSensors();
        
            //Collects data from sensors and store in 2D String array
            
            String[][] dataValues = Window.getDataValues();

            // Initialize JTable with column names and sensor data
            JTable table = Window.getTable();
            TableModel model = table.getModel();
            Window.setTable(table);
            JPanel panel = Window.getRightMainPanel();
            Window.setGridAlertColors(panel);
            panel.repaint();

            /*
            Window.createSensors();
            Window.setGridAlertColors(Window.rightMainPanel);
            Window.rightMainPanel.repaint();
            */
        }  
    }
}

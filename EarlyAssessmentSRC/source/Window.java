/**
 *
 * @author ethan.rae045
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Window extends JFrame
{
    public static final int WIDTH = 1200; 
    public static final int HEIGHT = 800;
    
    //no need for public static, used for testing generate button from Listener class
    public static ArrayList<Sensor> sensors;
    public static String[][] dataValues;
    public static JPanel rightMainPanel;

    public Window( )
    {
        super();
        
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setTitle("Early Warning System");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setup an exit button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new Listener( ));
        
        //setup generate new data button
        JButton newDataButton = new JButton("Generate Data");
        newDataButton.addActionListener(new Listener( ));

        //create main panels
        JPanel leftMainPanel = new JPanel(new BorderLayout());
        rightMainPanel = new JPanel(new GridLayout(32,32));
        JPanel leftMainHeaderPanel = new JPanel();
 
        leftMainPanel.setPreferredSize(new Dimension(400,800));
        rightMainPanel.setPreferredSize(new Dimension(800,600));
        
        //Create table for a list of sensors
        JTable table; 
        
        //Create scroll pane for the table to sit in
	JScrollPane scrollPane; 

	//Create JTable columns names
        String columnNames[] = { "Alert","ID", "Temp", "Humidity", "Battery Life", "Location" };
	
        //Create 1024 stub Sensor objects with random data variables
        createSensors();
        
        //Collects data from sensors and store in 2D String array
        getTableViewData();

	// Initialize JTable with column names and sensor data
	table = new JTable( dataValues, columnNames );
                
	// Add the filled JTable to a scrolling pane
	scrollPane = new JScrollPane(table);
        
	leftMainPanel.add(scrollPane, BorderLayout.CENTER );
        leftMainHeaderPanel.add(new JLabel("Sensor List"));
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.add(newDataButton);
        bottomButtonPanel.add(closeButton);
        leftMainPanel.add(bottomButtonPanel,BorderLayout.SOUTH);
        leftMainPanel.add(leftMainHeaderPanel,BorderLayout.PAGE_START);
        
        setGridAlertColors(rightMainPanel);
        
        // Adds left and right main panels to the main JFrame
        add(leftMainPanel,BorderLayout.WEST);
        add(rightMainPanel,BorderLayout.CENTER);

    }
    
    // Creates ArrayList of Sensor objects
    public static void createSensors()
    {
        sensors = new ArrayList(1024);
        Random r = new Random();
        for(int row = 0; row < 32; row++)
        {
            for(int col = 0; col < 32; col++)
            {
                int id;
                if(row == 0)
                {
                    id = col;
                }
                else
                    id = (row * 32) + col;
                
                int alertLevel = r.nextInt(4);
                double temp = 70.0 + (80.0 - 70.0) * r.nextDouble();
                double humidity = 0 + (100 - 0) * r.nextDouble();
                double batteryLife = 0 + (100 - 0) * r.nextDouble();

                Point location = new Point(row,col);

                sensors.add(new Sensor(id,alertLevel,temp,humidity,batteryLife,location)); 

            }
        }
    }
    
   // Converts ArrayList of Sensors into an 2d array of Strings for adding to a JTable
   public static void getTableViewData()
    {
        dataValues = new String[1024][6];
        for(int i = 0; i < dataValues.length; i++)
        {
            Sensor sensor = sensors.get(i);
            dataValues[i][0] =  sensor.getAlertLevel() + "";
            dataValues[i][1] =  sensor.getId() + "";
            dataValues[i][2] =  sensor.getTemp() + "";
            dataValues[i][3] =  sensor.getHumidity() + "";
            dataValues[i][4] =  sensor.getBatteryLife() + "";
            dataValues[i][5] =  sensor.getLocation().x + "," + sensor.getLocation().y ;
        }
    }

    public static void setGridAlertColors(JPanel grid) 
    {
        for(int i = 0; i < 1024; i++)
        {
            int alertColor = sensors.get(i).getAlertLevel();
            JLabel sensor = new JLabel(""+alertColor);
            switch(alertColor)
            {
                case 0:
                    sensor.setBackground(Color.green);
                    break;
                case 1:
                    sensor.setBackground(Color.yellow);
                    break;
                case 2:
                    sensor.setBackground(Color.orange);
                    break;
                case 3:
                    sensor.setBackground(Color.red);
                    break;
            }
            sensor.setOpaque(true);
            grid.add(sensor);
        }
    }
}

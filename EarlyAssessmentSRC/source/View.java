package source;

/**
 *
 * @author ethan.rae045
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class View extends JFrame
{
    public static final int WIDTH = 1200; 
    public static final int HEIGHT = 800;
    public static DataBase_Connector db_helper;
    public static JTable table_view;
    public static Table_Model table_model;
    public static String[][] dataValues;
    public static JPanel rightMainPanel;
    

    public static String[][] getDataValues() {
        return dataValues;
    }

    public static void setDataValues(String[][] dataValues) {
        View.dataValues = dataValues;
    }

    public static JPanel getRightMainPanel() {
        return rightMainPanel;
    }

    public static void setRightMainPanel(JPanel rightMainPanel) {
        View.rightMainPanel = rightMainPanel;
    }
    

    public static JTable getTable() {
        return table_view;
    }

    public static void setTable(JTable table) {
        View.table_view = table;
    }

    public View( )
    {
        super();
        
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setTitle("Early Warning System");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Controller listener = new Controller( );
        //setup an exit button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(listener);
        
        //setup generate new data button
        JButton newDataButton = new JButton("View Selection");
        newDataButton.addActionListener(listener);
        
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(listener);

        //create main panels
        JPanel leftMainPanel = new JPanel(new BorderLayout());
        rightMainPanel = new JPanel(new GridLayout(32,32));
        JPanel leftMainHeaderPanel = new JPanel();
 
        leftMainPanel.setPreferredSize(new Dimension(400,800));
        rightMainPanel.setPreferredSize(new Dimension(800,600));
        
        db_helper = new DataBase_Connector();
        db_helper.startDataBaseServer(); 
        
        //Create scroll pane for the table_view to sit in
	JScrollPane scrollPane;
        db_helper.updateDatabase("SENSORS", "time0.0_Sensors.txt");
	
        Object[][] db_sensors = db_helper.getSensors();
        Sensors_Model sensor_model = new Sensors_Model(db_sensors);
        table_model = new Table_Model(sensor_model);
        //Collects data from sensors and store in 2D String array

	// Initialize JTable with column names and sensor data
	table_view = new JTable(table_model);
        table_view.setAutoCreateRowSorter(true);
       
	// Add the filled JTable to a scrolling pane
	scrollPane = new JScrollPane(table_view);
        
	leftMainPanel.add(scrollPane, BorderLayout.CENTER );
        leftMainHeaderPanel.add(new JLabel("Sensor List"));
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.add(newDataButton);
        bottomButtonPanel.add(refresh);
        bottomButtonPanel.add(closeButton);
        leftMainPanel.add(bottomButtonPanel,BorderLayout.SOUTH);
        leftMainPanel.add(leftMainHeaderPanel,BorderLayout.PAGE_START);
        
        setGridAlertColors(rightMainPanel);
        
        // Adds left and right main panels to the main JFrame
        add(leftMainPanel,BorderLayout.WEST);
        add(rightMainPanel,BorderLayout.CENTER);

    }

    public static void setGridAlertColors(JPanel grid) 
    {
        Random rand = new Random();
        for(int i = 0; i < 1024; i++)
        {
            int alertColor = rand.nextInt(4);//TODO set according to new algorithm
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

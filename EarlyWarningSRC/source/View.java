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
import static source.Main.db_helper;

public class View extends JFrame
{
    public static final int WIDTH = 1200; 
    public static final int HEIGHT = 1000;
    
    public static JTable table_view;
    public static Table_Model table_model;
    public static JPanel rightMainPanel;
    public static JPanel leftMainPanel;
    
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
        
        //setup generate view selection button
        JButton newDataButton = new JButton("View Selection");
        newDataButton.addActionListener(listener);
        
        //setup generate view refresh button
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(listener);

        //create main panels
        leftMainPanel = new JPanel(new BorderLayout());
        JPanel topRightIdentifyPanel = new JPanel(new BorderLayout());
        topRightIdentifyPanel.add(new JLabel("AWARENESS LEVEL"),BorderLayout.PAGE_START);
        rightMainPanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(32,32));
        JPanel leftMainHeaderPanel = new JPanel();
 
        leftMainPanel.setPreferredSize(new Dimension(400,1000));
        rightMainPanel.setPreferredSize(new Dimension(800,1000));
        
        //Create scroll pane for the table_view to sit in
	JScrollPane scrollPane;
	
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
        
        setGridAlertColors(gridPanel);
        topRightIdentifyPanel.setPreferredSize(new Dimension(800,200));
        gridPanel.setPreferredSize(new Dimension(800,800));
        // Adds left and right main panels to the main JFrame
        add(leftMainPanel,BorderLayout.WEST);
        rightMainPanel.add(topRightIdentifyPanel,BorderLayout.PAGE_START);
        rightMainPanel.add(gridPanel,BorderLayout.CENTER);
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

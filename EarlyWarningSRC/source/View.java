package source;

/**
 *
 * @author ethan.rae045
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class View extends JFrame
{
    public static final int WIDTH = 1200; 
    public static final int HEIGHT = 1000;
    
    public static JTable table_view;
    public static Table_Model table_model;
    public static JPanel rightMainPanel;
    public static JPanel leftMainPanel;
    public static JPanel gridPanel;
    
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
        
        createMenuBar();
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
        gridPanel = new JPanel(new GridLayout(32,32));
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
        for(int i = 0; i < 1024; i++)
        {
            int alertColor = 2;
            double sensor_temp_at_index = (double)table_model.getValueAt(i, 2);
            //System.out.println("#" + i + " " + sensor_temp_at_index);
            if(sensor_temp_at_index <= Main.total_temp_avg)
                alertColor = 0;
            else if(sensor_temp_at_index <= (Main.total_temp_avg * (Main.total_temp_avg * 0.10)))
                alertColor = 1;
            else if(sensor_temp_at_index > (Main.total_temp_avg * (Main.total_temp_avg * 0.10)))
                alertColor = 2;

            JLabel sensor = new JLabel(" ●");
            switch(alertColor)
            {
                case 0:
                    sensor.setBackground(Color.green);
                    break;
                case 1:
                    sensor.setBackground(Color.yellow);
                    break;
                case 2:
                    sensor.setBackground(Color.red);
                    break;
            }
            sensor.setOpaque(true);
            grid.add(sensor);
        }
    }
    
    public static void updateCellColors()
    {
        Component[] comp = gridPanel.getComponents();
        for(int r = 0; r < 32; r++)
        {
            for(int c = 0; c < 32; c++)
            {
                int row_index = 0;
                if(r == 0)
                    row_index = r;
                else
                    row_index = (c * r) + c;
    
                Component cell = comp[row_index];
                int alertColor = 2;
                double sensor_temp_at_index = (double)table_model.getValueAt(row_index, 2);
                //System.out.println("#" + i + " " + sensor_temp_at_index);
                if(sensor_temp_at_index <= Main.total_temp_avg)
                    alertColor = 0;
                else if(sensor_temp_at_index <= (Main.total_temp_avg + (Main.total_temp_avg * 0.10)))
                    alertColor = 1;
                else if(sensor_temp_at_index > (Main.total_temp_avg + (Main.total_temp_avg * 0.10)))
                    alertColor = 2;

                switch(alertColor)
                {
                    case 0:
                        cell.setBackground(Color.green);
                        break;
                    case 1:
                        cell.setBackground(Color.yellow);
                        break;
                    case 2:
                        cell.setBackground(Color.red);
                        break;
                }
                cell.repaint();
            }
            gridPanel.repaint();
            
        }
        }

    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        JMenuItem menuItem = new JMenuItem("Close",KeyEvent.VK_C);
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
}

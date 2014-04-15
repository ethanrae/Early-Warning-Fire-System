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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class View extends JFrame {

    public static final int WIDTH = 1250;
    public static final int HEIGHT = 1000;

    public static JTable table_view;
    public static JLabel alertText;
    public static Table_Model table_model;
    public static JPanel rightMainPanel;
    public static JPanel leftMainPanel;
    public static JPanel gridPanel;
    public static JLabel alertLabel;
    public static JMenuItem export;
    public static Controller listener;

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

    public View() {
        super();

        
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setTitle("Early Warning System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setup the listener that manages are button clicks
        listener = new Controller();
        createMenuBar();

        //setup an exit button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(listener);

        //setup generate view selection button
        JButton view_selection_but = new JButton("View Selection");
        view_selection_but.addActionListener(listener);

        //setup generate view refresh_but button
        JButton refresh_but = new JButton("Refresh");
        refresh_but.addActionListener(listener);

        //create a container JPanel to hold avg temp and total awareness
        JPanel leftTopStatusPanel = new JPanel(new BorderLayout());
        leftTopStatusPanel.add(new JLabel("AWARENESS LEVEL"), BorderLayout.PAGE_START);
        leftTopStatusPanel.setPreferredSize(new Dimension(400, 120));
        alertText = new JLabel("\tAverage Temperature: ");
        alertText.setPreferredSize(new Dimension(50, 300));
        alertLabel = new JLabel();
        alertLabel.setOpaque(true);
        alertLabel.setPreferredSize(new Dimension(50, 50));
        alertLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        //leftTopStatusPanel.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        leftTopStatusPanel.add(alertLabel, BorderLayout.WEST);
        leftTopStatusPanel.add(alertText, BorderLayout.CENTER);

        //create a container JPanel to hold some buttons
        JPanel leftBottomButtonPanel = new JPanel();
        leftBottomButtonPanel.add(view_selection_but);
        leftBottomButtonPanel.add(refresh_but);
        leftBottomButtonPanel.add(closeButton);

        //create main panels
        leftMainPanel = new JPanel(new BorderLayout());
        rightMainPanel = new JPanel(new BorderLayout());
        rightMainPanel.setBackground(Color.black);
        rightMainPanel.setOpaque(true);

        leftMainPanel.setPreferredSize(new Dimension(400, 1000));
        rightMainPanel.setPreferredSize(new Dimension(800, 1000));

        // Initialize JTable with column names and sensor data
        table_view = new JTable(table_model);
        table_view.setAutoCreateRowSorter(true);

        //Create scroll pane for the table_view to sit in
        // Add the filled JTable to a scrolling pane
        JScrollPane scrollPane = new JScrollPane(table_view);

        leftMainPanel.add(scrollPane, BorderLayout.CENTER);
        leftMainPanel.add(new JLabel("Sensor List"), BorderLayout.NORTH);

        leftMainPanel.add(leftBottomButtonPanel, BorderLayout.SOUTH);
        leftMainPanel.add(leftTopStatusPanel, BorderLayout.PAGE_START);

        gridPanel = new JPanel(new GridLayout(32, 32));
        setGridAlertColors(gridPanel);
        gridPanel.setPreferredSize(new Dimension(800, 800));

        // Adds left and right main panels to the main JFrame
        add(leftMainPanel, BorderLayout.WEST);
        rightMainPanel.add(gridPanel, BorderLayout.CENTER);
        add(rightMainPanel, BorderLayout.CENTER);
        pack();
    }

    public static void setGridAlertColors(JPanel grid) {
        for (int i = 0; i < 1024; i++) {
            int alertColor = 2;
            double sensor_temp_at_index = (double) table_model.getValueAt(i, 2);
            //System.out.println("#" + i + " " + sensor_temp_at_index);
            if (sensor_temp_at_index <= Main.total_temp_avg) {
                alertColor = 0;
            } else if (sensor_temp_at_index <= (Main.total_temp_avg * (Main.total_temp_avg * 0.10))) {
                alertColor = 1;
            } else if (sensor_temp_at_index > (Main.total_temp_avg * (Main.total_temp_avg * 0.10))) {
                alertColor = 2;
            }

            JLabel sensor = new JLabel("●", JLabel.CENTER);
            //JLabel sensor = new JLabel();
            switch (alertColor) {
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
            sensor.setBorder(BorderFactory.createLineBorder(Color.black));
            sensor.setIgnoreRepaint(true);
            grid.add(sensor);
        }
    }

    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        JMenuItem menuItem = new JMenuItem("Close", KeyEvent.VK_C);
        export = new JMenuItem("Export to Excel Spreadsheet", KeyEvent.VK_E);
        menuItem.addActionListener(listener);
        export.addActionListener(listener);
        menu.add(menuItem);
        menu.add(export);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
}

package source;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import static source.Main.db_helper;
import static source.Main.listener;

/**
 *
 * @author E
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form NewView
     */
    public View() {
        table_model = new Table_Model(db_helper.getSensors());
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        left_Panel = new javax.swing.JPanel();
        alert_Level_Panel = new javax.swing.JPanel();
        avg_Temp_Img = new javax.swing.JLabel();
        avg_Temp_Text = new javax.swing.JLabel();
        table_Panel = new javax.swing.JPanel();
        table_Scroll_Panel = new javax.swing.JScrollPane();
        sensor_Table = new javax.swing.JTable();
        button_Panel = new javax.swing.JPanel();
        view_Selection_Button = new javax.swing.JButton();
        view_Selection_Button.addActionListener(listener);
        close_Button = new javax.swing.JButton();
        close_Button.addActionListener(listener);
        refresh_Button = new javax.swing.JButton();
        refresh_Button.addActionListener(listener);
        right_Panel = new javax.swing.JPanel();
        grid_Panel = new javax.swing.JPanel();
        menu_Bar = new javax.swing.JMenuBar();
        menu_File = new javax.swing.JMenu();
        menu_Export = new javax.swing.JMenuItem();
        menu_Export.addActionListener(listener);
        menu_Close = new javax.swing.JMenuItem();
        menu_Close.addActionListener(listener);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Early Warning System");
        setBounds(new java.awt.Rectangle(0, 22, 1043, 659));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("window"); // NOI18N

        left_Panel.setPreferredSize(new java.awt.Dimension(400, 630));

        alert_Level_Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        avg_Temp_Img.setText(" ");
        avg_Temp_Img.setOpaque(true);

        avg_Temp_Text.setText(" ");

        javax.swing.GroupLayout alert_Level_PanelLayout = new javax.swing.GroupLayout(alert_Level_Panel);
        alert_Level_Panel.setLayout(alert_Level_PanelLayout);
        alert_Level_PanelLayout.setHorizontalGroup(
            alert_Level_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alert_Level_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avg_Temp_Img, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(avg_Temp_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        alert_Level_PanelLayout.setVerticalGroup(
            alert_Level_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alert_Level_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(alert_Level_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(avg_Temp_Img, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(avg_Temp_Text))
                .addContainerGap())
        );

        table_Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        table_Scroll_Panel.setBorder(null);

        sensor_Table.setAutoCreateRowSorter(true);
        sensor_Table.setModel(table_model);
        table_Scroll_Panel.setViewportView(sensor_Table);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, button_Panel, org.jdesktop.beansbinding.ELProperty.create("true"), button_Panel, org.jdesktop.beansbinding.BeanProperty.create("opaque"));
        bindingGroup.addBinding(binding);

        view_Selection_Button.setText("View Selection");

        close_Button.setText("Close");

        refresh_Button.setText("Refresh");

        javax.swing.GroupLayout button_PanelLayout = new javax.swing.GroupLayout(button_Panel);
        button_Panel.setLayout(button_PanelLayout);
        button_PanelLayout.setHorizontalGroup(
            button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view_Selection_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refresh_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close_Button)
                .addContainerGap())
        );
        button_PanelLayout.setVerticalGroup(
            button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(close_Button)
                .addComponent(refresh_Button)
                .addComponent(view_Selection_Button))
        );

        javax.swing.GroupLayout table_PanelLayout = new javax.swing.GroupLayout(table_Panel);
        table_Panel.setLayout(table_PanelLayout);
        table_PanelLayout.setHorizontalGroup(
            table_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_Scroll_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
            .addComponent(button_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        table_PanelLayout.setVerticalGroup(
            table_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(table_PanelLayout.createSequentialGroup()
                .addComponent(table_Scroll_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout left_PanelLayout = new javax.swing.GroupLayout(left_Panel);
        left_Panel.setLayout(left_PanelLayout);
        left_PanelLayout.setHorizontalGroup(
            left_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(alert_Level_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(table_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        left_PanelLayout.setVerticalGroup(
            left_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left_PanelLayout.createSequentialGroup()
                .addComponent(alert_Level_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        right_Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        right_Panel.setOpaque(false);
        right_Panel.setPreferredSize(new java.awt.Dimension(625, 625));
        right_Panel.setLayout(new java.awt.BorderLayout());

        grid_Panel.setLayout(new java.awt.GridLayout(32, 32));
        right_Panel.add(grid_Panel, java.awt.BorderLayout.CENTER);

        menu_File.setText("File");

        menu_Export.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menu_Export.setText("Export Data to Excel Spreadsheet");
        menu_File.add(menu_Export);

        menu_Close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menu_Close.setText("Close");
        menu_File.add(menu_Close);

        menu_Bar.add(menu_File);

        setJMenuBar(menu_Bar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(left_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(right_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(right_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(left_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getAvg_Temp_Img() {
        return avg_Temp_Img;
    }

    public void setAvg_Temp_Img(JLabel avg_Temp_Img) {
        this.avg_Temp_Img = avg_Temp_Img;
    }

    public JLabel getAvg_Temp_Text() {
        return avg_Temp_Text;
    }

    public JPanel getAlert_Level_Panel() {
        return alert_Level_Panel;
    }

    public void setAlert_Level_Panel(JPanel alert_Level_Panel) {
        this.alert_Level_Panel = alert_Level_Panel;
    }

    public void setAvg_Temp_Text(JLabel avg_Temp_Text) {
        this.avg_Temp_Text = avg_Temp_Text;
    }

    public JPanel getRight_Panel() {
        return right_Panel;
    }

    public void setRight_Panel(JPanel right_Panel) {
        this.right_Panel = right_Panel;
    }

    public JTable getSensor_Table() {
        return sensor_Table;
    }

    public void setSensor_Table(JTable sensor_Table) {
        this.sensor_Table = sensor_Table;
    }

    public double getTotal_temp_avg() {
        return total_temp_avg;
    }

    public void setTotal_temp_avg(double total_temp_avg) {
        this.total_temp_avg = total_temp_avg;
    }

    public Table_Model getTable_model() {
        return table_model;
    }

    public void setTable_model(Table_Model table_model) {
        this.table_model = table_model;
    }

    public JPanel getGrid_Panel() {
        return grid_Panel;
    }

    public void setGrid_Panel(JPanel grid_Panel) {
        this.grid_Panel = grid_Panel;
    }

    private Table_Model table_model;
    private double total_temp_avg = 0.0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel alert_Level_Panel;
    private javax.swing.JLabel avg_Temp_Img;
    private javax.swing.JLabel avg_Temp_Text;
    private javax.swing.JPanel button_Panel;
    public static javax.swing.JButton close_Button;
    private javax.swing.JPanel grid_Panel;
    private javax.swing.JPanel left_Panel;
    private javax.swing.JMenuBar menu_Bar;
    public static javax.swing.JMenuItem menu_Close;
    public static javax.swing.JMenuItem menu_Export;
    private javax.swing.JMenu menu_File;
    public static javax.swing.JButton refresh_Button;
    private javax.swing.JPanel right_Panel;
    private javax.swing.JTable sensor_Table;
    private javax.swing.JPanel table_Panel;
    private javax.swing.JScrollPane table_Scroll_Panel;
    private javax.swing.JButton view_Selection_Button;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}

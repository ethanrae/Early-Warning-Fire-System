package source;

/**
 *
 * @author ethan.rae045
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import static source.Main.db_helper;
import static source.Main.view;

public class Button_Controller implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action: " + e.getActionCommand());

        //Should use switch statement
        if (e.getActionCommand().equalsIgnoreCase("Close")) {
            db_helper.shutdownDataBaseServer();
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("View Selection")) {
            JTable sensor_Table = view.getSensor_Table();
            Table_Model table_model = view.getTable_model();
            int[] selected_row_indexs = sensor_Table.getSelectedRows();
            if (selected_row_indexs.length > 0) {
                table_model.selected_sensors = table_model.getSelectedRows(selected_row_indexs);
                Table_Model.showing_all_sensors = false;
                table_model.fireTableDataChanged();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Refresh")) //could be else
        {
            Table_Model table_model = view.getTable_model();
            JPanel avg_temp_panel = view.getAlert_Level_Panel();
            table_model.selected_sensors = null;
            Table_Model.showing_all_sensors = true;
            Mouse_Controller.resetSelectionQueue();
            table_model.fireTableDataChanged();
            avg_temp_panel.revalidate();
            avg_temp_panel.repaint();
        } else if (e.getActionCommand().equalsIgnoreCase("Export Data to Excel Spreadsheet")) {

            File_Chooser fc = new File_Chooser();
            new Thread(fc).start();
        }
    }
}

package source;

/**
 *
 * @author ethan.rae045
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static source.Main.db_helper;
import static source.View.table_model;
import static source.View.table_view;

public class Controller implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action: " + e.getActionCommand());

        //Should use switch statement
        if (e.getActionCommand().equalsIgnoreCase("Close")) {
            db_helper.shutdownDataBaseServer();
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("View Selection")) {
            int[] selected_row_indexs = table_view.getSelectedRows();

            if (selected_row_indexs.length > 0) {
                table_model.selected_sensors = table_model.getSelectedRows(selected_row_indexs);
                Table_Model.showing_all_sensors = false;
                table_model.fireTableDataChanged();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Refresh")) //could be else
        {
            table_model.selected_sensors = null;
            Table_Model.showing_all_sensors = true;
            table_model.fireTableDataChanged();
        } else if (e.getActionCommand().equalsIgnoreCase("Export to Excel Spreadsheet")) {

                File_Chooser fc = new File_Chooser();
                new Thread(fc).start();
        }
    }
}

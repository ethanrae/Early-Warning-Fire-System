package source;

/**
 *
 * @author ethan.rae045
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static source.Main.DataUpdateThread;
import static source.Main.db_helper;
import static source.Main.thread;
import static source.Main.window;
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
                Sensors_Model selected = new Sensors_Model(table_model.getSelectedRows(selected_row_indexs));
                table_model.setSelected_sensors(selected);
                Table_Model.showing_all_sensors = false;
                table_model.fireTableDataChanged();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Refresh")) //could be else
        {
            table_model.setSelected_sensors(null);
            Table_Model.showing_all_sensors = true;
            table_model.fireTableDataChanged();
        } else if (e.getActionCommand().equalsIgnoreCase("Export to Excel Spreadsheet")) {

                File_Chooser fc = new File_Chooser();
                new Thread(fc).start();
                
                /*
                int result;
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("title");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //
                // disable the "All files" option.
                //
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.showDialog(null, "SAVE");
                //
                System.out.println("getCurrentDirectory(): "
                +  chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : "
                +  chooser.getSelectedFile());
                }
                */

        }

    }

}

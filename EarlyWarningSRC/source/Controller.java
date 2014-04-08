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

public class Controller implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //System.out.println(e.getActionCommand());
        
        //Should use switch statement
        if(e.getActionCommand().equalsIgnoreCase("Close"))
        {
            db_helper.shutdownDataBaseServer();
            System.exit(0);
        }
        else if (e.getActionCommand().equalsIgnoreCase("View Selection"))
        {
            int[] selected_row_indexs = table_view.getSelectedRows();
            
            if(selected_row_indexs.length > 0)
            {
                Sensors_Model selected = new Sensors_Model(table_model.getSelectedRows(selected_row_indexs));
                table_model.setSelected_sensors(selected);
                Table_Model.showing_all_sensors = false;
                table_model.fireTableDataChanged();
            }
        }
        else if (e.getActionCommand().equalsIgnoreCase("Refresh")) //could be else
        {
                table_model.setSelected_sensors(null);
                Table_Model.showing_all_sensors = true;
                table_model.fireTableDataChanged();
        } 
    }
}

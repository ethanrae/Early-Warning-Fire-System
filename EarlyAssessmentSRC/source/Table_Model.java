
package source;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author E
 */

public class Table_Model extends AbstractTableModel {

    private final String columnNames[] = { "Time","Sensor ID", "Temp", "Humidity", "Light", "Voltage" };
    public Sensors_Model all_sensors;
    public Sensors_Model selected_sensors;
    public static boolean showing_all_sensors;
    
    public Table_Model(Sensors_Model sensors)
    {
        this.all_sensors = sensors;
        this.selected_sensors = new Sensors_Model();
        showing_all_sensors = true;
    }
    
    public Sensors_Model getAll_sensors() {
        return all_sensors;
    }

    public void setAll_sensors(Sensors_Model all_sensors) {
        this.all_sensors = all_sensors;
    }

    public Sensors_Model getSelected_sensors() {
        return selected_sensors;
    }

    public void setSelected_sensors(Sensors_Model selected_sensors) {
        this.selected_sensors = selected_sensors;
    }
    @Override
    public int getRowCount() {
        if(all_sensors == null)
            return 0;
        if(showing_all_sensors)
            return all_sensors.getNumberOfRows();
        else
            return selected_sensors.getNumberOfRows();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(showing_all_sensors)
            return all_sensors.sensors[rowIndex][columnIndex];
        else
            return selected_sensors.sensors[rowIndex][columnIndex];
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(showing_all_sensors)
            all_sensors.sensors[rowIndex][columnIndex] = value;
        else
            selected_sensors.sensors[rowIndex][columnIndex] = value;

        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public String getColumnName(int columnIndex) {
    return columnNames[columnIndex];
}
    
    public Object[][] getSelectedRows(int[] selected_index)
    {
        Object[][] selectedRows = new Object[selected_index.length][6];
        for(int rowIndex = 0; rowIndex < selected_index.length; rowIndex++)
        {
            if(showing_all_sensors)
            {
                selectedRows[rowIndex] = all_sensors.sensors[selected_index[rowIndex]];
            }
            else
            {
                selectedRows[rowIndex] = selected_sensors.sensors[selected_index[rowIndex]];
            }
                
        }
        return selectedRows;
    }
    
    
}

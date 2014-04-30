package source;

import java.util.PriorityQueue;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author E
 */
public class Table_Model extends AbstractTableModel {

    private final String columnNames[] = {"Time", "Sensor ID", "Temp", "Humidity", "Light", "Voltage"};
    public Object[][] all_sensors;
    public Object[][] selected_sensors;
    public static boolean showing_all_sensors;

    public Table_Model(Object[][] sensors) {
        this.all_sensors = sensors;
        this.selected_sensors = null;
        showing_all_sensors = true;
    }

    @Override
    public int getRowCount() {
        if (all_sensors == null) {
            return 0;
        }
        if (showing_all_sensors) {
            return all_sensors.length;
        } else {
            return selected_sensors.length;
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (showing_all_sensors) {
            return all_sensors[rowIndex][columnIndex];
        } else {
            return selected_sensors[rowIndex][columnIndex];
        }
    }

    public Object getValueAtFromAllSensors(int rowIndex, int columnIndex) {
        return all_sensors[rowIndex][columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) == null) {
            return String.class;
        } else {
            return getValueAt(0, columnIndex).getClass();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (showing_all_sensors) {
            all_sensors[rowIndex][columnIndex] = value;
        } else {
            selected_sensors[rowIndex][columnIndex] = value;
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Object[][] getSelectedRows(int[] selected_index) {
        Object[][] selectedRows = new Object[selected_index.length][6];
        for (int rowIndex = 0; rowIndex < selected_index.length; rowIndex++) {
            if (showing_all_sensors) {
                selectedRows[rowIndex] = all_sensors[selected_index[rowIndex]];
            } else {
                selectedRows[rowIndex] = selected_sensors[selected_index[rowIndex]];
            }

        }
        return selectedRows;
    }

    public Object[][] getSelectedRows(PriorityQueue selected_index) {
        Object[] selected_index_array = selected_index.toArray();
        Object[][] selectedRows = new Object[selected_index.size()][6];
        for (int rowIndex = 0; rowIndex < selected_index.size(); rowIndex++) {

            selectedRows[rowIndex] = all_sensors[(Integer) selected_index_array[rowIndex]];

        }
        return selectedRows;
    }

}//end of class

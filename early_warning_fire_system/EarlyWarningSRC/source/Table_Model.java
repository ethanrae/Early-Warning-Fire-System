package source;

import java.util.PriorityQueue;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import static source.Main.view;

/**
 *
 * @author E
 */
public class Table_Model extends AbstractTableModel {

    private final String columnNames[] = {"Time", "Sensor ID", "Temp", "Humidity", "Light", "Voltage"};
    public Vector all_sensors;
    public Vector selected_sensors;
    public static boolean showing_all_sensors;

    public Table_Model(Vector sensors) {
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
            return all_sensors.size();
        } else {
            return selected_sensors.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (showing_all_sensors) {
            return ((Object) ((Sensor) all_sensors.get(rowIndex)).getColumnSensorData(columnIndex));
        } else {
            return ((Object) ((Sensor) selected_sensors.get(rowIndex)).getColumnSensorData(columnIndex));
        }
    }

    public Object getValueAtFromAllSensors(int rowIndex, int columnIndex) {
        return ((Object) ((Sensor) all_sensors.get(rowIndex)).getColumnSensorData(columnIndex));
    }

    public void removeRow(int rowId) {
        JPanel grid_panel = view.getGrid_Panel();
        if (showing_all_sensors) {
            for (int i = 0; i < all_sensors.size(); i++) {
                try {
                    if (((Sensor) all_sensors.get(i)).getId() == rowId) {
                        Grid_Cell cell = (Grid_Cell) grid_panel.getComponent(((Sensor) all_sensors.get(i)).getId() - 1);
                        all_sensors.remove(i);
                        cell.setNotSelected();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < selected_sensors.size(); i++) {
                try {
                    if (((Sensor) selected_sensors.get(i)).getId() == rowId) {
                        Grid_Cell cell = (Grid_Cell) grid_panel.getComponent(((Sensor) selected_sensors.get(i)).getId() - 1);
                        selected_sensors.remove(i);
                        cell.setNotSelected();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        fireTableDataChanged();
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
            ((Sensor) all_sensors.get(rowIndex)).setColumnSensorData(columnIndex, value);
        } else {
            ((Sensor) selected_sensors.get(rowIndex)).setColumnSensorData(columnIndex, value);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setValueAtRow(Object[] data, int rowIndex) {
        for (int i = 0; i < columnNames.length; i++) {
            if (showing_all_sensors) {
                ((Sensor) all_sensors.get(rowIndex)).setColumnSensorData(i, data[i]);
            } else {
                ((Sensor) selected_sensors.get(rowIndex)).setColumnSensorData(i, data[i]);
            }
        }
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public int[] getSelectedRowsFromSelected(int[] selected_from_selected) {
        int selectedToModel[] = new int[selected_from_selected.length];
        JTable sensor_table = view.getSensor_Table();
        for (int i = 0; i < selected_from_selected.length; i++) {
            int index = sensor_table.convertRowIndexToModel(selected_from_selected[i]);
            selectedToModel[i] = index;
        }
        return selectedToModel;
    }

    public int getRowPoint(int rowIndex) {
        System.out.println("Row Point: " + rowIndex);
        int id = (int) getValueAtFromAllSensors(rowIndex, 1);
        return id - 1;
    }

    public Vector getSelectedRows(PriorityQueue selected_grid_cells) {
        Object[] selected_index_array = selected_grid_cells.toArray();
        Vector selectedRows = new Vector(selected_grid_cells.size());
        for (int rowIndex = 0; rowIndex < selected_grid_cells.size(); rowIndex++) {
            int index = ((Grid_Cell) selected_index_array[rowIndex]).getIndex();

            double time = (double) getValueAt(index, 0);
            int id = (int) getValueAt(index, 1);
            double temp = (double) getValueAt(index, 2);
            double hum = (double) getValueAt(index, 3);
            double light = (double) getValueAt(index, 4);
            double voltage = (double) getValueAt(index, 5);
            selectedRows.add(new Sensor(time, id, temp, hum, light, voltage));
        }
        return selectedRows;
    }
    
    public Vector getSelectedRowsFromAll(PriorityQueue selected_grid_cells) {
        Object[] selected_index_array = selected_grid_cells.toArray();
        Vector selectedRows = new Vector(selected_grid_cells.size());
        for (int rowIndex = 0; rowIndex < selected_grid_cells.size(); rowIndex++) {
            int index = ((Grid_Cell) selected_index_array[rowIndex]).getIndex();

            double time = (double) getValueAtFromAllSensors(index, 0);
            int id = (int) getValueAtFromAllSensors(index, 1);
            double temp = (double) getValueAtFromAllSensors(index, 2);
            double hum = (double) getValueAtFromAllSensors(index, 3);
            double light = (double) getValueAtFromAllSensors(index, 4);
            double voltage = (double) getValueAtFromAllSensors(index, 5);
            selectedRows.add(new Sensor(time, id, temp, hum, light, voltage));
        }
        return selectedRows;
    }

}//end of class

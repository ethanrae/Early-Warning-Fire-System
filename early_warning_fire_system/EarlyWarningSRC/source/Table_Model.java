package source;

import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import static source.Main.NUM_OF_SENSORS;
import static source.Main.db_helper;
import static source.Main.main_view;

/**
 *
 * @author E
 */
public class Table_Model extends AbstractTableModel {

    private final String[] column_names = {"Time", "Sensor ID", "Temp", "Humidity", "Light", "Voltage"};
    public Vector all_sensors;
    public Vector selected_sensors;
    public static boolean showing_all;

    public Table_Model(Vector sensors) {
        this.all_sensors = sensors;
        this.selected_sensors = null;
        showing_all = true;
    }

    @Override
    public int getRowCount() {
        if (all_sensors == null) {
            return 0;
        }
        if (showing_all) {
            return all_sensors.size();
        } else {
            return selected_sensors.size();
        }
    }

    @Override
    public int getColumnCount() {
        return column_names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (showing_all) {
            return ((Object) ((Sensor) all_sensors.get(rowIndex)).getColumnSensorData(columnIndex));
        } else {
            return ((Object) ((Sensor) selected_sensors.get(rowIndex)).getColumnSensorData(columnIndex));
        }
    }

    public Object getValueAtFromAllSensors(int rowIndex, int columnIndex) {
        return ((Object) ((Sensor) all_sensors.get(rowIndex)).getColumnSensorData(columnIndex));
    }

    public void removeRow(int rowId) {
        //Having an empty table creates null problems
        if (getRowCount() < 2) {
            return;//don't do anything 
        }
        JPanel grid_panel = main_view.getGrid_Panel();

        //Fine the matching id and delete it
        if (showing_all) {
            for (int i = 0; i < all_sensors.size(); i++) {
                try {
                    if (((Sensor) all_sensors.get(i)).getId() == rowId) {
                        Grid_Cell cell = (Grid_Cell) grid_panel.getComponent(((Sensor) all_sensors.get(i)).getId() - 1);
                        all_sensors.remove(i);
                        cell.setBackground(Color.BLACK);
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
                        cell.setBackground(Color.BLACK);
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
        if (showing_all) {
            ((Sensor) all_sensors.get(rowIndex)).setColumnSensorData(columnIndex, value);
        } else {
            ((Sensor) selected_sensors.get(rowIndex)).setColumnSensorData(columnIndex, value);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setValueAtRow(Object[] data, int rowIndex) {
        try {
            if (rowIndex >= 0 && rowIndex < NUM_OF_SENSORS) {
                for (int i = 0; i < column_names.length; i++) {
                    if (showing_all) {
                        ((Sensor) all_sensors.get(rowIndex)).setColumnSensorData(i, data[i]);
                    } else {
                        ((Sensor) selected_sensors.get(rowIndex)).setColumnSensorData(i, data[i]);
                    }
                }

            } else {
                Sensor newSensor = new Sensor((double) data[0], (int) data[1], (double) data[2], (double) data[3], (double) data[4], (double) data[5]);
                if (showing_all) {
                    all_sensors.add(rowIndex, newSensor);
                } else {
                    selected_sensors.add(rowIndex, newSensor);
                }
                db_helper.insertData(newSensor);
                this.fireTableDataChanged();
            }
            Update_Sensors_TimerTask.updateGridCellColors();
        } catch (Exception ex) {
        }
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return column_names[columnIndex];
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

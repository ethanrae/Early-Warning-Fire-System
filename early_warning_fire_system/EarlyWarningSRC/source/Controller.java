package source;

/**
 *
 * @author ethan.rae045
 */
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.PriorityQueue;
import javax.swing.JPanel;
import javax.swing.JTable;
import static source.Main.NUM_OF_SENSORS;
import static source.Main.db_helper;
import static source.Main.view;

public class Controller implements ActionListener, MouseListener {

    private Point clicked_point;
    private Point released_point;
    private final static PriorityQueue<Grid_Cell> selectedCells = new PriorityQueue<Grid_Cell>();

    public Controller() {

    }

    public static PriorityQueue<Grid_Cell> getSelectedIndexs() {
        return selectedCells;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Grid_Cell cell = (Grid_Cell) view.getGrid_Panel().getComponentAt(e.getPoint());
        this.clicked_point = new Point(cell.getCellX(), cell.getCellY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Grid_Cell cell = (Grid_Cell) view.getGrid_Panel().getComponentAt(e.getPoint());
        if (cell != null) {
            this.released_point = new Point(cell.getCellX(), cell.getCellY());
            addSelectedRows(this.clicked_point, this.released_point);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void resetSelectionQueue() {
        Object[] selected_cells_array = selectedCells.toArray();
        for (int i = 0; i < selected_cells_array.length; i++) {
            ((Grid_Cell) selected_cells_array[i]).setNotSelected();
        }
        selectedCells.clear();
    }

    private void removeExcludedFromSelection(Object[] oldSelected) {
        for (int i = 0; i < oldSelected.length; i++) {
            if (!selectedCells.contains((Grid_Cell) oldSelected[i])) {
                ((Grid_Cell) oldSelected[i]).setNotSelected();
            }
        }
    }

    private void addSelectedRows(Point C, Point R) {
        JPanel grid_panel = view.getGrid_Panel();
        Grid_Cell cell;

        //Implementation of drag mouse selection based off points, author Ethan Rae
        int x, y, xMax, yMax;
        if (C.x <= R.x && C.y <= R.y) {
            //System.out.println("Top Left to  Bot Right");
            x = C.x;
            y = C.y;
            xMax = R.x;
            yMax = R.y;
        } else if (C.x <= R.x && C.y >= R.y) {
            //System.out.println("Top Right to Bot Left");
            x = C.x;
            y = R.y;
            xMax = R.x;
            yMax = C.y;
        } else if (C.x >= R.x && C.y <= R.y) {
            //System.out.println("Bot Left to Top Right");
            x = R.x;
            y = C.y;
            xMax = C.x;
            yMax = R.y;
        } else if (C.x >= R.x && C.y >= R.y) {
            //System.out.println("Bot Right to Top Left");
            x = R.x;
            y = R.y;
            xMax = C.x;
            yMax = C.y;
        } else//never will happen, mutually exclusive, wanna see if jarch catches it
        {
            //System.out.println("Equal");
            x = R.x;
            xMax = R.x;
            y = R.y;
            yMax = y;
        }
        //System.out.println(x + "->" + xMax +  " " + y + "->" + yMax);
        //Looks better than copy/paste multiple for loops, author Ethan Rae
        for (int l = x; l <= xMax; l++) {
            for (int s = y; s <= yMax; s++) {
                int index = (l * 32) + s;

                cell = (Grid_Cell) grid_panel.getComponent(index);

                if (!selectedCells.contains(cell)) {
                    if (!cell.isSelected()) {
                        cell.setSelected();
                    }
                }
            }
        }
        changeSelectedSensorsTable();
    }

    private void changeSelectedSensorsTable() {
        Table_Model table_model = view.getTable_model();
        if (!selectedCells.isEmpty()) {
            table_model.selected_sensors = table_model.getSelectedRowsFromAll(selectedCells);
            Table_Model.showing_all_sensors = false;
            table_model.fireTableDataChanged();
        }
    }

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
            JPanel grid_panel = view.getGrid_Panel();
            Grid_Cell cell;

            //get selected rows, might be sorted
            int[] selected_row_indexs = sensor_Table.getSelectedRows();

            //convert indexes from how the look now, sorted or not, to the original model indexs
            for (int i = 0; i < selected_row_indexs.length; i++) {
                int index = sensor_Table.convertRowIndexToModel(selected_row_indexs[i]);
                int selected_id = (int) table_model.getValueAt(index, 1);
                //int indexToView;
                selected_row_indexs[i] = selected_id - 1;
                //indexToView = sensor_Table.convertRowIndexToView(index);
                //System.out.println("\nIndex: " + index + " > Model: " + selected_row_indexs[i]);
                //System.out.println("Index: " + index + " > View: " + indexToView + "\n");
            }

            //copy old selectedCells before we 
            Object[] oldSelectedCells = selectedCells.toArray();

            //clear selected cells, we have old copy
            selectedCells.clear();

            //Add each new selected cell to selected
            if (selected_row_indexs.length > 0) {
                for (int i = 0; i < selected_row_indexs.length; i++) {
                    cell = (Grid_Cell) grid_panel.getComponent(selected_row_indexs[i]);
                    cell.setSelected();
                }

                //Unselected each cell that wasn't in new selection
                removeExcludedFromSelection(oldSelectedCells);

                //Set the view selected_sensors using our selectedCells
                table_model.selected_sensors = table_model.getSelectedRowsFromAll(selectedCells);

                //Tell our table model its going to be showing the selected sensors
                Table_Model.showing_all_sensors = false;

                //setSelectedGridBoarders(); 
                //Not needed since borders are 
                //set when added or removed from selectedCells
                table_model.fireTableDataChanged();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Refresh")) //could be else
        {
            Table_Model table_model = view.getTable_model();
            JPanel avg_temp_panel = view.getAlert_Level_Panel();
            table_model.selected_sensors = null;
            Table_Model.showing_all_sensors = true;
            resetSelectionQueue();
            table_model.fireTableDataChanged();
            avg_temp_panel.revalidate();
            avg_temp_panel.repaint();
        } else if (e.getActionCommand().equalsIgnoreCase("Export Data to Excel Spreadsheet")) {

            File_Chooser fc = new File_Chooser();
            new Thread(fc).start();
        } else if (e.getActionCommand().equalsIgnoreCase("Edit Sensor")) {
            Edit_Sensor_Dialog dialog = new Edit_Sensor_Dialog(view, true);
            JTable sensor_Table = view.getSensor_Table();
            Table_Model table_model = view.getTable_model();
            int selected_index = sensor_Table.getSelectedRow();
            if (selected_index >= 0 && selected_index < table_model.getRowCount()) {
                int selected_row_index = sensor_Table.convertRowIndexToModel(selected_index);
                if (selected_row_index >= 0 && selected_row_index < NUM_OF_SENSORS) {

                    dialog.getId_Text().setText(table_model.getValueAt(selected_row_index, 1).toString());
                    dialog.getTemp_Text().setText(table_model.getValueAt(selected_row_index, 2).toString());
                    dialog.getHum_Text().setText(table_model.getValueAt(selected_row_index, 3).toString());
                    dialog.getLight_Text().setText(table_model.getValueAt(selected_row_index, 4).toString());
                    dialog.getVoltage_Text().setText(table_model.getValueAt(selected_row_index, 5).toString());
                    dialog.setEdit_index(selected_row_index);
                } else {
                    dialog.setEdit_index(-1);
                }
            }
            dialog.setVisible(true);
        } else if (e.getActionCommand().equalsIgnoreCase("Remove Sensor By ID")) {
            Remove_Sensor_Dialog dialog = new Remove_Sensor_Dialog(view, true);
            dialog.setVisible(true);
        }
    }
}


package source;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.PriorityQueue;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import static source.Main.view;

/**
 *
 * @author E
 */
public class Mouse_Controller implements MouseListener {

    private Point clicked_point;
    private Point released_point;
    public static PriorityQueue<Integer> selectedIndexs;

    public Mouse_Controller() {
        this.selectedIndexs = new PriorityQueue<Integer>();
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
        this.released_point = new Point(cell.getCellX(), cell.getCellY());
        addSelectedRows(this.clicked_point, this.released_point);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void resetSelectionQueue() {
        Object[] select_index_array = selectedIndexs.toArray();
        for(int i = 0; i < select_index_array.length; i++)
        {
            Grid_Cell cell = (Grid_Cell)view.getGrid_Panel().getComponent((int)select_index_array[i]);
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setSelected();
        }
            
        selectedIndexs = new PriorityQueue<Integer>();
    }

    private void addSelectedRows(Point C, Point R) {
        JPanel grid_panel = view.getGrid_Panel();
        Grid_Cell cell;
        
        //Implementation of drag mouse selection based off points, author Ethan Rae
        int x,y,xMax,yMax;
        if(C.x <= R.x && C.y <= R.y)
        {
            //System.out.println("Top Left to  Bot Right");
            x = C.x;
            y = C.y;
            xMax = R.x;
            yMax = R.y;
        }
        else if(C.x <= R.x && C.y >= R.y)
        {
            //System.out.println("Top Right to Bot Left");
            x = C.x;
            y = R.y;
            xMax = R.x;
            yMax = C.y;
        }
        else if(C.x >= R.x && C.y <= R.y)
        {
            //System.out.println("Bot Left to Top Right");
            x = R.x;
            y = C.y;
            xMax = C.x;
            yMax = R.y;
        }
        else if(C.x >= R.x && C.y >= R.y)
        {
            //System.out.println("Bot Right to Top Left");
            x = R.x;
            y = R.y;
            xMax = C.x;
            yMax = C.y;
        }
        else//never will happen, mutually exclusive, wanna see if jarch catches it
        {
            //System.out.println("Equal");
            x = R.x;
            xMax = R.x;
            y = R.y;
            yMax = y;    
        }
        //System.out.println(x + "->" + xMax +  " " + y + "->" + yMax);
        //Looks better than copy/paste multiple for loops, author Ethan Rae
        for (int l = x;l <= xMax; l++) {
            for (int s = y; s <= yMax; s++) {
                int index = (l * 32) + s;
                //System.out.println("Index " + x + "," + y + " = " + index);
                if (!selectedIndexs.contains(index)) {
                    selectedIndexs.add(index);
                    cell = (Grid_Cell) grid_panel.getComponent(index);
                    if (!cell.isSelected()) {
                        cell.setSelected();
                    }
                }
            }
        }
        changeSelectedSensorsTable();
    }

    private void changeSelectedSensorsTable() {
        JTable sensor_Table = view.getSensor_Table();
        Table_Model table_model = view.getTable_model();
        if (this.selectedIndexs.size() > 0) {
            table_model.selected_sensors = table_model.getSelectedRows(this.selectedIndexs);
            Table_Model.showing_all_sensors = false;
            table_model.fireTableDataChanged();
        }
    }

}

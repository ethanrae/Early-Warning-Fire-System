package source;

import java.awt.Color;
import java.awt.GridLayout;
import static java.lang.Thread.sleep;
import java.util.TimerTask;
import javax.swing.JPanel;
import static source.Main.db_helper;
import static source.Main.view;

/**
 *
 * @author E
 */
public class Sensor_Update_TimerTask extends TimerTask {

    private int current_file_index;
    private final static String[] sensor_filenames = {"time0.0_Sensors.txt", "time0.5_Sensors.txt", "time1.0_Sensors.txt", "time1.5_Sensors.txt", "time2.0_Sensors.txt", "time2.5_Sensors.txt", "time3.0_Sensors.txt", "time3.5_Sensors.txt", "time4.0_Sensors.txt", "time4.5_Sensors.txt", "time5.0_Sensors.txt"}; //The time incremented data text files for 1024 sensors each
    private final static String table_name = "SENSORS"; //The name of the data base table used in the program

    public Sensor_Update_TimerTask() {
        current_file_index = 0;
    }

    @Override
    public void run() {
        //update the data base with new data
        db_helper.updateDatabase(table_name, sensor_filenames[current_file_index]);

        this.current_file_index++;

        if (current_file_index == sensor_filenames.length) {
            this.current_file_index = 0;
        }
        while (view == null) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }

        Table_Model table_model = view.getTable_model();
        //update table model with new data from database
        table_model.all_sensors = db_helper.getSensors();

        updateGridCellColors();

        //notify the table to update its view
        table_model.fireTableDataChanged();

    }

    private void updateGridCellColors() {
        if (Table_Model.showing_all_sensors) {
            try {

                JPanel right_Panel = view.getRight_Panel();
                double total_temp_avg = view.getTotal_temp_avg();
                Table_Model table_model = view.getTable_model();
                Mouse_Controller mc = new Mouse_Controller();

                double sensor_temp_at_index;
                final double avg_plus_ten_perc = (total_temp_avg + (total_temp_avg * 0.10));
                JPanel newGrid = new JPanel(new GridLayout(32, 32));
                for (int x = 0; x < 32; x++) {
                    for (int y = 0; y < 32; y++) {

                        Grid_Cell cell = new Grid_Cell(x, y);

                        sensor_temp_at_index = (double) table_model.getValueAtFromAllSensors((x * 32) + y, 2);

                        if (sensor_temp_at_index <= total_temp_avg) {
                            cell.setBackground(Color.GREEN);
                        } else if (sensor_temp_at_index <= avg_plus_ten_perc) {
                            cell.setBackground(Color.YELLOW);
                        } else {
                            cell.setBackground(Color.RED);
                        }
                        newGrid.add(cell);
                    }
                }
                right_Panel.removeAll();
                view.setGrid_Panel(newGrid);
                view.getGrid_Panel().addMouseListener(mc);
                right_Panel.add(view.getGrid_Panel());
                right_Panel.revalidate();
                right_Panel.repaint();

            } catch (Exception ex) {
            }
        }
    }

}

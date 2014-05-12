package source;

import java.awt.Color;
import java.awt.GridLayout;
import static java.lang.Thread.sleep;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static source.Main.db_helper;
import static source.Main.main_view;

/**
 *
 * @author E
 */
public class Update_Sensors_TimerTask extends TimerTask {

    private int current_file_index;
    private final static String[] sensor_filenames = {"time0.0_Sensors.txt", "time0.5_Sensors.txt", "time1.0_Sensors.txt", "time1.5_Sensors.txt", "time2.0_Sensors.txt", "time2.5_Sensors.txt", "time3.0_Sensors.txt", "time3.5_Sensors.txt", "time4.0_Sensors.txt", "time4.5_Sensors.txt", "time5.0_Sensors.txt"}; //The time incremented data text files for 1024 sensors each
    private final static String table_name = "SENSORS"; //The name of the data base table used in the program
    public final static double MIN_SENSOR_VOLTAGE = 1.5;
    private final static double MAX_TEMP = 40.0;

    public Update_Sensors_TimerTask() {
        current_file_index = 0;
    }

    @Override
    public void run() {

        if (Table_Model.showing_all) {
            //update the data base with new data
            db_helper.updateDatabase(table_name, sensor_filenames[current_file_index]);
        }

        this.current_file_index++;

        if (current_file_index == sensor_filenames.length) {
            this.current_file_index = 0;
        }
        while (main_view == null) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }

        Table_Model table_model = main_view.getTable_model();
        //update table model with new data from database
        table_model.all_sensors = db_helper.getSensorFromDB();

        updateGridCellColors();

    }

    public static void updateGridCellColors() {
        if (Table_Model.showing_all) {
            try {

                JPanel right_Panel = main_view.getRight_Panel();
                double total_temp_avg = main_view.getTotal_temp_avg();
                double total_lum_avg = main_view.getTotal_lum_avg();
                double total_hum_avg = main_view.getTotal_hum_avg();
                Table_Model table_model = main_view.getTable_model();

                double sensor_temp_at_index;
                double sensor_lum_at_index;
                double sensor_hum_at_index;
                double sensor_volt_at_index;
                final double avg_temp_plus_ten_perc = (total_temp_avg + (total_temp_avg * 0.10));
                final double avg_lum_plus_ten_perc = (total_lum_avg + (total_lum_avg * 0.10));
                final double avg_hum_plus_ten_perc = (total_hum_avg + (total_hum_avg * 0.10));
                JPanel newGrid = new JPanel(new GridLayout(32, 32));
                for (int x = 0; x < 32; x++) {
                    for (int y = 0; y < 32; y++) {

                        Grid_Cell cell = new Grid_Cell(x, y);

                        sensor_temp_at_index = (double) table_model.getValueAtFromAllSensors((x * 32) + y, 2);
                        sensor_lum_at_index = (double) table_model.getValueAtFromAllSensors((x * 32) + y, 3);
                        sensor_hum_at_index = (double) table_model.getValueAtFromAllSensors((x * 32) + y, 4);
                        sensor_volt_at_index = (double) table_model.getValueAtFromAllSensors((x * 32) + y, 5);
                        if (sensor_volt_at_index < MIN_SENSOR_VOLTAGE) {
                            cell.setBackground(new Color(205,132,6));
                        } else if (sensor_temp_at_index <= total_temp_avg && sensor_lum_at_index <= avg_lum_plus_ten_perc && sensor_hum_at_index <= avg_hum_plus_ten_perc) {
                            cell.setBackground(Color.GREEN);
                        } else if (sensor_temp_at_index < MAX_TEMP && sensor_lum_at_index <= avg_lum_plus_ten_perc && sensor_hum_at_index <= avg_hum_plus_ten_perc) {
                            cell.setBackground(Color.YELLOW);
                        } else {
                            cell.setBackground(Color.RED);
                            JLabel avg_Temp_Img = main_view.getAvg_Temp_Img();
                            avg_Temp_Img.setBackground(Color.RED);
                        }
                        newGrid.add(cell);
                    }
                }
                main_view.getGrid_Panel().removeMouseListener(Main.listener);
                right_Panel.removeAll();
                main_view.setGrid_Panel(newGrid);
                main_view.getGrid_Panel().addMouseListener(Main.listener);
                right_Panel.add(main_view.getGrid_Panel());
                right_Panel.revalidate();
                right_Panel.repaint();
                //notify the table to update its main_view
                table_model.fireTableDataChanged();
            } catch (Exception ex) {
            }
        }
    }

}

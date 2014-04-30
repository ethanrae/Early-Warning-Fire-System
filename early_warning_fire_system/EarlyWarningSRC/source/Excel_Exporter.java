package source;

import java.io.File;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import static source.Main.view;

/**
 *
 * @author E
 */
public class Excel_Exporter {

    public void fillData(File file) {
        Table_Model table_model = view.getTable_model();
        double total_temp_avg = view.getTotal_temp_avg();
        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("Sheet1", 0);

            Label header = new Label(0, 0, "Total Temp Avg");
            sheet1.addCell(header);
            Label total_temp_avg_label = new Label(1, 0, String.valueOf(total_temp_avg));
            sheet1.addCell(total_temp_avg_label);

            for (int i = 0; i < table_model.getColumnCount(); i++) {
                Label column = new Label(i, 1, table_model.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < table_model.getRowCount(); i++) {
                for (j = 0; j < table_model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 2,
                            table_model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

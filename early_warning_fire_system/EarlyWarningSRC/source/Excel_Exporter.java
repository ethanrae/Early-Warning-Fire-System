/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import static source.Main.total_temp_avg;
import static source.View.table_model;

/**
 *
 * @author E
 */
public class Excel_Exporter {

    public void fillData(File file) {

        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);

            Label header = new Label(0, 0, "Simple Moving Average");
            sheet1.addCell(header);
            Label simple_moving_average = new Label(1, 0, String.valueOf(total_temp_avg));
            sheet1.addCell(simple_moving_average);

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

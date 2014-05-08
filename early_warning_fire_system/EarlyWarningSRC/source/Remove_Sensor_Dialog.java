package source;

import static source.Main.NUM_OF_SENSORS;
import static source.Main.main_view;

/**
 *
 * @author E
 */
public class Remove_Sensor_Dialog extends javax.swing.JDialog {

    /**
     * Creates new form Remove_Sensor_Dialog
     */
    public Remove_Sensor_Dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        remove_Sensor_Id_Text = new javax.swing.JTextField();
        cancel_Remove_Sensor_But = new javax.swing.JButton();
        ok_Remove_Sensor_But = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remove Sensor");
        setType(java.awt.Window.Type.POPUP);

        jLabel1.setText("Sensor ID");

        cancel_Remove_Sensor_But.setText("Cancel");
        cancel_Remove_Sensor_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_Remove_Sensor_ButActionPerformed(evt);
            }
        });

        ok_Remove_Sensor_But.setText("Ok");
        ok_Remove_Sensor_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ok_Remove_Sensor_ButActionPerformed(evt);
            }
        });

        jLabel2.setText("Example: 1,2,3-4,6-7,111");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ok_Remove_Sensor_But)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel_Remove_Sensor_But, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(remove_Sensor_Id_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(remove_Sensor_Id_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_Remove_Sensor_But)
                    .addComponent(ok_Remove_Sensor_But))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ok_Remove_Sensor_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ok_Remove_Sensor_ButActionPerformed

        try {
            //regex pattern
            String pattern = "(((\\d+)|(\\d+-{1}\\d+))($|,{1}))+";

            //Removes all whitespace from input  
            String input = this.remove_Sensor_Id_Text.getText().replace(" ", "");
            if (!input.equals("")) {

                //Removes trailing commas input
                if (input.charAt(input.length() - 1) == ',') {
                    input = input.substring(0, input.length() - 1);
                }

                //Input validation
                if (input.matches(pattern)) {

                    //Parse input
                    String delims = "[,]";
                    String[] parsedInput = input.split(delims);

                    for (String string : parsedInput) {
                        Table_Model table_model = main_view.getTable_model();
                        if (string.matches("\\d+"))//single digit
                        {
                            int rowId = Integer.parseInt(string);
                            table_model.removeRow(rowId);
                        } else if (string.matches("\\d+-{1}\\d+"))//Range
                        {
                            String[] range = string.split("[-]");
                            int startId = Integer.parseInt(range[0]);
                            int finishId = Integer.parseInt(range[1]);
                            if (startId < finishId && startId > 0 && finishId <= NUM_OF_SENSORS) {
                                for (int i = startId; i <= finishId; i++) {
                                    table_model.removeRow(i);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            //Close dialog
            this.dispose();
        }

    }//GEN-LAST:event_ok_Remove_Sensor_ButActionPerformed

    private void cancel_Remove_Sensor_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_Remove_Sensor_ButActionPerformed
        //Close dialog do nothing
        this.dispose();
    }//GEN-LAST:event_cancel_Remove_Sensor_ButActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Remove_Sensor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Remove_Sensor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Remove_Sensor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Remove_Sensor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Remove_Sensor_Dialog dialog = new Remove_Sensor_Dialog(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_Remove_Sensor_But;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton ok_Remove_Sensor_But;
    private javax.swing.JTextField remove_Sensor_Id_Text;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static source.Main.window;

/**
 *
 * @author E
 */
public class File_Chooser implements Runnable {
    private String save_directory;

    public File_Chooser()
    {
        save_directory = "";
    }
    public String getSave_directory() {
        return save_directory;
    }
    @Override
    public void run() {
        JFileChooser fc = new JFileChooser();
        
        fc.setDialogTitle("Select a directory");
        fc.setCurrentDirectory(new java.io.File("."));
        fc.removeChoosableFileFilter(fc.getFileFilter() );
        fc.setFileFilter(new FileNameExtensionFilter("Microsoft Excel Documents",".xls"));
        int returnVal = fc.showSaveDialog(window);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            //System.out.println("getSelectedFile() : " + fc.getSelectedFile());
            save_directory = fc.getSelectedFile().getPath();
            Excel_Exporter ee = new Excel_Exporter();
                File output_file = new File(save_directory + ".xls");
            try {
                output_file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(File_Chooser.class.getName()).log(Level.SEVERE, null, ex);
            }
                ee.fillData(output_file);
        } else 
        {
            //user pressed cancel
        }
    }

}

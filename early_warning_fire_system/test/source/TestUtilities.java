/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam.wardell303
 */
public class TestUtilities {
    
    public static void StartMain()
    {
        String[] str = {""};
        Main.main(str);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Excel_ExporterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

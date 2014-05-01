/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adam.wardell303
 */
public class File_ChooserTest {
    
    public File_ChooserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TestUtilities.StartMain();
    }

    /**
     * Test of getSave_directory method, of class File_Chooser.
     */
    @Test
    public void testGetSave_directory() {
        System.out.println("getSave_directory");
        File_Chooser instance = new File_Chooser();
        String expResult = "";
        String result = instance.getSave_directory();
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class File_Chooser.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        File_Chooser fc = new File_Chooser();
        new Thread(fc).start();
    }
    
}

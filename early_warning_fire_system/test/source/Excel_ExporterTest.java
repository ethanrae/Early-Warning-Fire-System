/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Excel_ExporterTest {
    
    public Excel_ExporterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TestUtilities.StartMain();
    }
    
    /**
     * Test of fillData method, of class Excel_Exporter.
     */
    @Test
    public void testFillData() {
        System.out.println("fillData");
        File file = new File("testFillData.xls");
        Excel_Exporter instance = new Excel_Exporter();
        instance.fillData(file);
    }
    
}

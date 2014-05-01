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
public class Grid_CellTest {
    
    private static Grid_Cell instance = null;
    
    public Grid_CellTest() {
    }
    
    @Before
    public void setUp() {
        GenerateInstance();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
    
    private static void GenerateInstance()
    {
        instance = new Grid_Cell(0, 0);
    }

    /**
     * Test of getCellX method, of class Grid_Cell.
     */
    @Test
    public void testGetCellX() {
        System.out.println("getCellX");
        int expResult = 0;
        int result = instance.getCellX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCellY method, of class Grid_Cell.
     */
    @Test
    public void testGetCellY() {
        System.out.println("getCellY");
        int expResult = 0;
        int result = instance.getCellY();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCellX method, of class Grid_Cell.
     */
    @Test
    public void testSetCellX() {
        System.out.println("setCellX");
        int x = 1;
        instance.setCellX(x);
        int expResult = 1;
        int result = instance.getCellX();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCellY method, of class Grid_Cell.
     */
    @Test
    public void testSetCellY() {
        System.out.println("setCellY");
        int y = 1;
        instance.setCellY(y);
        int expResult = 1;
        int result = instance.getCellY();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSelected method, of class Grid_Cell.
     */
    @Test
    public void testSetSelected() {
        System.out.println("setSelected");
        instance.setSelected();
        boolean expResult = true;
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSelected method, of class Grid_Cell.
     */
    @Test
    public void testIsSelected() {
        System.out.println("isSelected");
        boolean expResult = false;
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
    }
    
}

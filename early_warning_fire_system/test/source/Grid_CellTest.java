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
    
    public Grid_CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCellX method, of class Grid_Cell.
     */
    @Test
    public void testGetCellX() {
        System.out.println("getCellX");
        Grid_Cell instance = null;
        int expResult = 0;
        int result = instance.getCellX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCellY method, of class Grid_Cell.
     */
    @Test
    public void testGetCellY() {
        System.out.println("getCellY");
        Grid_Cell instance = null;
        int expResult = 0;
        int result = instance.getCellY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCellX method, of class Grid_Cell.
     */
    @Test
    public void testSetCellX() {
        System.out.println("setCellX");
        int x = 0;
        Grid_Cell instance = null;
        instance.setCellX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCellY method, of class Grid_Cell.
     */
    @Test
    public void testSetCellY() {
        System.out.println("setCellY");
        int y = 0;
        Grid_Cell instance = null;
        instance.setCellY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelected method, of class Grid_Cell.
     */
    @Test
    public void testSetSelected() {
        System.out.println("setSelected");
        Grid_Cell instance = null;
        instance.setSelected();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSelected method, of class Grid_Cell.
     */
    @Test
    public void testIsSelected() {
        System.out.println("isSelected");
        Grid_Cell instance = null;
        boolean expResult = false;
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

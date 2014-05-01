/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.util.PriorityQueue;
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
public class Table_ModelTest {
    
    public Table_ModelTest() {
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
     * Test of getRowCount method, of class Table_Model.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        Table_Model instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class Table_Model.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        Table_Model instance = null;
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class Table_Model.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int rowIndex = 0;
        int columnIndex = 0;
        Table_Model instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAtFromAllSensors method, of class Table_Model.
     */
    @Test
    public void testGetValueAtFromAllSensors() {
        System.out.println("getValueAtFromAllSensors");
        int rowIndex = 0;
        int columnIndex = 0;
        Table_Model instance = null;
        Object expResult = null;
        Object result = instance.getValueAtFromAllSensors(rowIndex, columnIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnClass method, of class Table_Model.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int columnIndex = 0;
        Table_Model instance = null;
        Class expResult = null;
        Class result = instance.getColumnClass(columnIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCellEditable method, of class Table_Model.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int rowIndex = 0;
        int columnIndex = 0;
        Table_Model instance = null;
        boolean expResult = false;
        boolean result = instance.isCellEditable(rowIndex, columnIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueAt method, of class Table_Model.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object value = null;
        int rowIndex = 0;
        int columnIndex = 0;
        Table_Model instance = null;
        instance.setValueAt(value, rowIndex, columnIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnName method, of class Table_Model.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int columnIndex = 0;
        Table_Model instance = null;
        String expResult = "";
        String result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedRows method, of class Table_Model.
     */
    @Test
    public void testGetSelectedRows_intArr() {
        System.out.println("getSelectedRows");
        int[] selected_index = null;
        Table_Model instance = null;
        Object[][] expResult = null;
        Object[][] result = instance.getSelectedRows(selected_index);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedRows method, of class Table_Model.
     */
    @Test
    public void testGetSelectedRows_PriorityQueue() {
        System.out.println("getSelectedRows");
        PriorityQueue selected_index = null;
        Table_Model instance = null;
        Object[][] expResult = null;
        Object[][] result = instance.getSelectedRows(selected_index);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

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
    
    private static Table_Model instance = null;
    private static Sensor[][] sensors = null;
    private static final int width = 4;
    private static final int height = 3;
    
    public Table_ModelTest() {
    }
    
    @Before
    public void setUp() {
        GenerateInstance();
    }
    
    @After
    public void tearDown() {
        instance = null;
        sensors = null;
    }
    
    private static void GenerateInstance()
    {
        sensors = new Sensor[width][height];
        
        for (int x = 0; x < width; ++x)
        {
            for (int y = 0; y < height; ++y)
            {
                sensors[x][y] = new Sensor(0, 0, 0, 0, 0, 0);
            }
        }
        
        instance = new Table_Model(sensors);
    }

    /**
     * Test of getRowCount method, of class Table_Model.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = width;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnCount method, of class Table_Model.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 6;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAt method, of class Table_Model.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int rowIndex = 0;
        int columnIndex = 0;
        Object expResult = sensors[rowIndex][columnIndex];
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAtFromAllSensors method, of class Table_Model.
     */
    @Test
    public void testGetValueAtFromAllSensors() {
        System.out.println("getValueAtFromAllSensors");
        int rowIndex = 0;
        int columnIndex = 0;
        Object expResult = sensors[rowIndex][columnIndex];
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of isCellEditable method, of class Table_Model.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int rowIndex = 0;
        int columnIndex = 0;
        boolean expResult = false;
        boolean result = instance.isCellEditable(rowIndex, columnIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of setValueAt method, of class Table_Model.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object value = new Sensor(1, 1, 1, 1, 1, 1);
        int rowIndex = 0;
        int columnIndex = 0;
        instance.setValueAt(value, rowIndex, columnIndex);
        Object expResult = value;
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getColumnName method, of class Table_Model.
     */
    @Test
    public void testGetColumnName() {
        //{"Time", "Sensor ID", "Temp", "Humidity", "Light", "Voltage"}
        System.out.println("getColumnName");
        int columnIndex = 0;
        String expResult = "Time";
        String result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        
        columnIndex = 1;
        expResult = "Sensor ID";
        result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        
        columnIndex = 2;
        expResult = "Temp";
        result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        
        columnIndex = 3;
        expResult = "Humidity";
        result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        
        columnIndex = 4;
        expResult = "Light";
        result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        
        columnIndex = 5;
        expResult = "Voltage";
        result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
    }
    
}

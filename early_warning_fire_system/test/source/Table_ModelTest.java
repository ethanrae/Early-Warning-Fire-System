
package source;

import java.util.Vector;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;



/**
 *
 * @author adam.wardell303
 */
public class Table_ModelTest {
    
    private static Table_Model instance = null;
    private static Vector sensors = null;
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
        sensors = new Vector();
        
        for (int x = 0; x < width*height; ++x)
        {
            for (int y = 0; y < height; ++y)
            {
                sensors.add(new Sensor(0, 0, 0, 0, 0, 0));
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
        int expResult = 36;
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
        Object expResult = ((Sensor)sensors.get(rowIndex)).getColumnSensorData(columnIndex);
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
        Object expResult = ((Sensor)sensors.get(rowIndex)).getColumnSensorData(columnIndex);
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
        Object value = new Double(1.9);
        int rowIndex = 0;
        int columnIndex = 0;
        instance.setValueAt(value, rowIndex, columnIndex);
        Object expResult = new Double(1.9);
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

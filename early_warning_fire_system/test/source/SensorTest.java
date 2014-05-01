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
public class SensorTest {
    
    public SensorTest() {
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
     * Test of getTime method, of class Sensor.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Sensor instance = new Sensor();
        double expResult = 0.0;
        double result = instance.getTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime method, of class Sensor.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        double time = 0.0;
        Sensor instance = new Sensor();
        instance.setTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Sensor.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Sensor instance = new Sensor();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Sensor.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Sensor instance = new Sensor();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemp method, of class Sensor.
     */
    @Test
    public void testGetTemp() {
        System.out.println("getTemp");
        Sensor instance = new Sensor();
        double expResult = 0.0;
        double result = instance.getTemp();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTemp method, of class Sensor.
     */
    @Test
    public void testSetTemp() {
        System.out.println("setTemp");
        double temp = 0.0;
        Sensor instance = new Sensor();
        instance.setTemp(temp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHum method, of class Sensor.
     */
    @Test
    public void testGetHum() {
        System.out.println("getHum");
        Sensor instance = new Sensor();
        double expResult = 0.0;
        double result = instance.getHum();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHum method, of class Sensor.
     */
    @Test
    public void testSetHum() {
        System.out.println("setHum");
        double hum = 0.0;
        Sensor instance = new Sensor();
        instance.setHum(hum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLight method, of class Sensor.
     */
    @Test
    public void testGetLight() {
        System.out.println("getLight");
        Sensor instance = new Sensor();
        double expResult = 0.0;
        double result = instance.getLight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLight method, of class Sensor.
     */
    @Test
    public void testSetLight() {
        System.out.println("setLight");
        double light = 0.0;
        Sensor instance = new Sensor();
        instance.setLight(light);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVoltage method, of class Sensor.
     */
    @Test
    public void testGetVoltage() {
        System.out.println("getVoltage");
        Sensor instance = new Sensor();
        double expResult = 0.0;
        double result = instance.getVoltage();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVoltage method, of class Sensor.
     */
    @Test
    public void testSetVoltage() {
        System.out.println("setVoltage");
        double voltage = 0.0;
        Sensor instance = new Sensor();
        instance.setVoltage(voltage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

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
    
    private static Sensor instance = null;
    
    public SensorTest() {
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
        instance = new Sensor(0, 0, 0, 0, 0, 0);
    }

    /**
     * Test of getTime method, of class Sensor.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        double expResult = 0.0;
        double result = instance.getTime();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setTime method, of class Sensor.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        double time = 1.0;
        instance.setTime(time);
        double expResult = 1.0;
        double result = instance.getTime();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getId method, of class Sensor.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Sensor.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        instance.setId(id);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemp method, of class Sensor.
     */
    @Test
    public void testGetTemp() {
        System.out.println("getTemp");
        double expResult = 0.0;
        double result = instance.getTemp();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setTemp method, of class Sensor.
     */
    @Test
    public void testSetTemp() {
        System.out.println("setTemp");
        double temp = 1.0;
        instance.setTemp(temp);
        double expResult = 1.0;
        double result = instance.getTemp();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getHum method, of class Sensor.
     */
    @Test
    public void testGetHum() {
        System.out.println("getHum");
        double expResult = 0.0;
        double result = instance.getHum();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setHum method, of class Sensor.
     */
    @Test
    public void testSetHum() {
        System.out.println("setHum");
        double hum = 1.0;
        instance.setHum(hum);
        double expResult = 1.0;
        double result = instance.getHum();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getLight method, of class Sensor.
     */
    @Test
    public void testGetLight() {
        System.out.println("getLight");
        double expResult = 0.0;
        double result = instance.getLight();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setLight method, of class Sensor.
     */
    @Test
    public void testSetLight() {
        System.out.println("setLight");
        double light = 1.0;
        instance.setLight(light);
        double expResult = 1.0;
        double result = instance.getLight();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getVoltage method, of class Sensor.
     */
    @Test
    public void testGetVoltage() {
        System.out.println("getVoltage");
        double expResult = 0.0;
        double result = instance.getVoltage();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setVoltage method, of class Sensor.
     */
    @Test
    public void testSetVoltage() {
        System.out.println("setVoltage");
        double voltage = 1.0;
        instance.setVoltage(voltage);
        double expResult = 1.0;
        double result = instance.getVoltage();
        assertEquals(expResult, result, 0.01);
    }
    
}

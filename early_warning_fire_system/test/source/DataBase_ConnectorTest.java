/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import static java.lang.Thread.sleep;
import java.util.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static source.Main.NUM_OF_SENSORS;
import static source.Main.db_helper;

/**
 *
 * @author adam.wardell303
 */
public class DataBase_ConnectorTest {
    
    private static DataBase_Connector instance;
    
    public DataBase_ConnectorTest() {
    }
    
    @Before
    public void setUp() {
        GenerateInstance();
    }
    
    @After
    public void tearDown() {
        ShutdownInstance();
    }
    
    private static void GenerateInstance()
    {
        instance = new DataBase_Connector();
        instance.startDBServer();
        while (!db_helper.isServerRunning()) {
            System.out.println("Server Not Started");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
        System.out.println("Server Started");
    }
    
    private static void ShutdownInstance()
    {
        instance.shutdownDBServer();
        instance = null;
    }

    /**
     * Test of printDebugDB method, of class DataBase_Connector.
     */
    @Test
    public void testViewData() {
        System.out.println("viewData");
        instance.printDebugDB();
    }

    /**
     * Test of updateDatabase method, of class DataBase_Connector.
     */
    @Test
    public void testUpdateDatabase() {
        System.out.println("updateDatabase");
        String table_name = "SENSORS";
        String file_name = "time0.0_Sensors.txt";
        instance.updateDatabase(table_name, file_name);
    }

    /**
     * Test of insertData method, of class DataBase_Connector.
     */
    @Test
    public void testInsertData() {
        System.out.println("insertData");
        Sensor S = new Sensor(1025.0,1025,1025.0,1025.0,1025.0,1025.0);
        instance.insertData(S);
    }

    /**
     * Test of isServerRunning method, of class DataBase_Connector.
     */
    @Test
    public void testHostAvailabilityCheck() {
        System.out.println("hostAvailabilityCheck");
        boolean expResult = true;
        boolean result = instance.isServerRunning();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class DataBase_Connector.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Data Base Connector";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}

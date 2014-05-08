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
     * Test of deleteTable method, of class DataBase_Connector.
     */
    @Test
    public void testDeleteTable() {
        System.out.println("deleteTable");
        DataBase_Connector instance = new DataBase_Connector();
        instance.deleteTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTable method, of class DataBase_Connector.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        DataBase_Connector instance = new DataBase_Connector();
        instance.createTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printDebugDB method, of class DataBase_Connector.
     */
    @Test
    public void testViewData() {
        System.out.println("viewData");
        DataBase_Connector instance = new DataBase_Connector();
        instance.printDebugDB();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDatabase method, of class DataBase_Connector.
     */
    @Test
    public void testUpdateDatabase() {
        System.out.println("updateDatabase");
        String table_name = "";
        String file_name = "";
        DataBase_Connector instance = new DataBase_Connector();
        instance.updateDatabase(table_name, file_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertData method, of class DataBase_Connector.
     */
    @Test
    public void testInsertData() {
        System.out.println("insertData");
        Sensor S = null;
        DataBase_Connector instance = new DataBase_Connector();
        instance.insertData(S);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startDBServer method, of class DataBase_Connector.
     */
    @Test
    public void testStartDataBaseServer() {
        System.out.println("startDataBaseServer");
        DataBase_Connector instance = new DataBase_Connector();
        instance.startDBServer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutdownDBServer method, of class DataBase_Connector.
     */
    @Test
    public void testShutdownDataBaseServer() {
        System.out.println("shutdownDataBaseServer");
        DataBase_Connector instance = new DataBase_Connector();
        instance.shutdownDBServer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isServerRunning method, of class DataBase_Connector.
     */
    @Test
    public void testHostAvailabilityCheck() {
        System.out.println("hostAvailabilityCheck");
        DataBase_Connector instance = new DataBase_Connector();
        boolean expResult = false;
        boolean result = instance.isServerRunning();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DataBase_Connector.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DataBase_Connector instance = new DataBase_Connector();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSensorFromDB method, of class DataBase_Connector.
     */
    @Test
    public void testGetSensors() {
        System.out.println("getSensors");
        DataBase_Connector instance = new DataBase_Connector();
        Object[] expResult = null;
        Object[] result = instance.getSensorFromDB().toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

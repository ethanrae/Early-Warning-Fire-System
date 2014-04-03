
package source;

/**
 *
 * @author E
 */

public class Sensors_Model {
    //Holds sensor info that will fill the tables rows and columns
    public Object[][] sensors;

    public Object[][] getSensors() {
        return sensors;
    }

    public void setSensors(Object[][] sensors) {
        this.sensors = sensors;
    }
    
    public Sensors_Model()
    {
        this.sensors = null;
    }
    
    public Sensors_Model(Object[][] sensors)
    {
        this.sensors = new Object[1024][6];
        this.sensors = sensors;
        /*for(int r = 0; r < sensors.length; r++)
            for(int c = 0; c < sensors[r].length; c++)
                this.sensors[r][c] = sensors[r][c];*/
    }
    
    public int getNumberOfRows()
    {
        return this.sensors.length;
    }
    
    public int getNumberOfColumns()
    {
        return this.sensors[0].length;
    }
    
    public void swapRows(int toIndex, int fromIndex)
    {
        Object[] temp = sensors[toIndex];
        sensors[toIndex] = sensors[fromIndex];
        sensors[fromIndex] = temp;
    }
}

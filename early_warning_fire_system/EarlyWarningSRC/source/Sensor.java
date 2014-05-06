package source;

/**
 *
 * @author Ethan
 */
public class Sensor {

    double time;
    int id;
    double temp;
    double hum;
    double light;
    double voltage;

    public Sensor() {

    }

    public Sensor(double time, int id, double temp, double hum, double light, double voltage) {
        this.time = time;
        this.id = id;
        this.temp = temp;
        this.hum = hum;
        this.light = light;
        this.voltage = voltage;
    }

    public Object getColumnSensorData(int columnIndex) {
        Object data = new Object();
        switch (columnIndex) {
            case 0:
                return getTime();
            case 1:
                return getId();
            case 2:
                return getTemp();
            case 3:
                return getHum();
            case 4:
                return getLight();
            case 5:
                return getVoltage();
        }
        return data;
    }

    public void setColumnSensorData(int columnIndex, Object data) {
        switch (columnIndex) {
            case 0:
                setTime((double) data);
                break;
            case 1:
                setId((int) data);
                break;
            case 2:
                setTemp((double) data);
                break;
            case 3:
                setHum((double) data);
                break;
            case 4:
                setLight((double) data);
                break;
            case 5:
                setVoltage((double) data);
                break;
        }

    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHum() {
        return hum;
    }

    public void setHum(double hum) {
        this.hum = hum;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }
}

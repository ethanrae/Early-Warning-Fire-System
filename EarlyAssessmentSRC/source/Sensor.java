/**
 *
 * @author Ethan
 */

import java.awt.Point;

public class Sensor {
    int id;
    int alertLevel;
    double temp;
    double humidity;
    double batteryLife;
    Point location;

    public Sensor()
    {
        
    }
    //may need to overload without alertLevel preset...
    public Sensor(int id, int alertLevel, double temp, double humidity, double batteryLife, Point location)
    {
        this.id = id;
        this.alertLevel = alertLevel;
        this.temp = temp;
        this.humidity = humidity;
        this.batteryLife = batteryLife;
        this.location = location;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(int alertLevel) {
        this.alertLevel = alertLevel;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(double batteryLife) {
        this.batteryLife = batteryLife;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
    
}

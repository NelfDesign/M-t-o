package fr.nelfdesign.meteonelf;

import java.io.Serializable;

public class ClimatInfos implements Serializable{

    int id;
    float temp;
    float temp_max;
    float temp_min;
    float pressure;
    float vent;
    int humidity;
    String weather;

    public ClimatInfos(int id, float temp, float temp_max, float temp_min,
                       float pressure, float vent, int humidity,
                       String weather) {
        this.id = id;
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.pressure = pressure;
        this.vent = vent;
        this.humidity = humidity;
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp_max() {
        return temp_max;

    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getVent() {
        return vent;
    }

    public void setVent(float vent) {
        this.vent = vent;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}

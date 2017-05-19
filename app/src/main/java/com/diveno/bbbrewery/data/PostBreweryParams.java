package com.diveno.bbbrewery.data;

/**
 * Created by Diego on 12/01/2017.
 */

public class PostBreweryParams {
    private int temperature;
    private int humity;
    private long time;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumity() {
        return humity;
    }

    public void setHumity(int humity) {
        this.humity = humity;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

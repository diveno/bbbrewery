package com.diveno.bbbrewery.main;

import android.graphics.Color;
import android.util.Log;

import com.diveno.bbbrewery.BoardDefaults;
import com.diveno.bbbrewery.Pixel;
import com.google.android.things.contrib.driver.apa102.Apa102;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Diego on 12/01/2017.
 */
public class MainPresenter {

    // LED configuration.
    private static final int NUM_LEDS = 7;
    private static final int LED_BRIGHTNESS = 5; // 0 ... 31

    Apa102 sensor;

    public MainPresenter() {
        int[] ledColors = new int[NUM_LEDS];

        try {
            sensor = new Apa102(BoardDefaults.getSPIPort(), Apa102.Mode.RGB);
            sensor.write(ledColors);
            sensor.setBrightness(LED_BRIGHTNESS);
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void drawPixels(ArrayList<Pixel> pixels) {
        //Write colors on the pixels
    }
}

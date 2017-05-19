package com.diveno.bbbrewery.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.PeripheralManagerService;

/**
 * Created by diego on 9/01/17.
 */

public class MainActivity extends Activity {


    private MainPresenter presenter;


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PeripheralManagerService service = new PeripheralManagerService();
        presenter = new MainPresenter();
        Log.d(TAG, "Available GPIO: " + service.getGpioList());

    }
}

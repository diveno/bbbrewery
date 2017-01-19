package com.diveno.bbbrewery;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.diveno.bbbrewery.main.MainPresenter;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Diego on 19/01/2017.
 */

public class ScreenService extends Service {
    private static final int LEDS = 6 - 1;
    ArrayList<Pixel> pixels;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display screen = windowManager.getDefaultDisplay();
        int maxX = screen.getWidth();
        int maxY = screen.getHeight();


        Pixel pixel1 = new Pixel();
        pixel1.setX(0);
        pixel1.setY(0);
        pixel1.setColor(getPixelColor(pixel1.getX(), pixel1.getY()));
        pixels.add(pixel1);


        Pixel pixel2 = new Pixel();
        pixel2.setX(maxX / 2);
        pixel2.setY(0);
        pixel2.setColor(getPixelColor(pixel2.getX(), pixel2.getY()));
        pixels.add(pixel2);


        Pixel pixel3 = new Pixel();
        pixel1.setX(maxX);
        pixel1.setY(0);
        pixel1.setColor(getPixelColor(pixel3.getX(), pixel3.getY()));
        pixels.add(pixel3);


        Pixel pixel4 = new Pixel();
        pixel4.setX(0);
        pixel4.setY(maxY);
        pixel4.setColor(getPixelColor(pixel4.getX(), pixel4.getY()));
        pixels.add(pixel4);

        Pixel pixel5 = new Pixel();
        pixel5.setX(maxX / 2);
        pixel5.setY(maxY);
        pixel5.setColor(getPixelColor(pixel5.getX(), pixel5.getY()));
        pixels.add(pixel5);

        Pixel pixel6 = new Pixel();
        pixel6.setX(maxX);
        pixel6.setY(maxY);
        pixel6.setColor(getPixelColor(pixel6.getX(), pixel6.getY()));
        pixels.add(pixel6);


        getPixelColor(screen.getWidth(), screen.getHeight());
        MainPresenter presenter = new MainPresenter();
        presenter.drawPixels(pixels);
        return START_STICKY;
    }

    private int getPixelColor(int xcoord, int ycoord) {
        try {
            Process sh = Runtime.getRuntime().exec("su", null, null);

            OutputStream os = sh.getOutputStream();
            os.write(("/system/bin/screencap -p " + Environment.getExternalStorageDirectory() + File.separator +
                    "colorPickerTemp.png").getBytes("ASCII"));
            os.flush();

            os.close();
            sh.waitFor();

            Bitmap screen = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "colorPickerTemp.png");
            int pixel = screen.getPixel(xcoord, ycoord);
            Log.d("pixel color", "Pixel Color: + " + Integer.toHexString(pixel) + " at x:" + xcoord + " y:" + ycoord);
            return pixel;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

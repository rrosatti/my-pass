package com.example.rodri.mypass.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.example.rodri.mypass.R;

/**
 * Created by rodri on 4/2/2016.
 */
public class SplashScreenActivity extends Activity {

    protected boolean _active = true;
    protected int _splashTime = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        /** Display will be used to get the width and height of the phone screen */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        System.out.println("width: " + width + " height: " + height);

        ImageView img = (ImageView) findViewById(R.id.lockImageView);
        img.setImageResource(R.drawable.key);
        img.getLayoutParams().width = (int) (0.70 * width);
        img.getLayoutParams().height = (int) (0.45 * height);
        img.requestLayout();

        System.out.println("width: " + (int) (0.6 * width) + " height: " + (int) (0.3 * height));

        final Thread splahThread = new Thread() {
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) waited += 100;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    Intent startApp = new Intent(SplashScreenActivity.this, AccountsActivity.class);
                    startActivity(startApp);
                }
            }
        };
        splahThread.start();

    }

}

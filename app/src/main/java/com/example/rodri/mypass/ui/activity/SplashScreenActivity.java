package com.example.rodri.mypass.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.rodri.mypass.R;

/**
 * Created by rodri on 4/2/2016.
 */
public class SplashScreenActivity extends Activity {

    protected boolean _active = true;
    protected int _splashTime = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ImageView img = (ImageView) findViewById(R.id.lockImageView);
        img.setImageResource(R.drawable.lock);

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

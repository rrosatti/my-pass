package com.example.rodri.mypass.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rodri.mypass.R;
import com.example.rodri.mypass.ui.adapter.UtilFunctions;

/**
 * Created by rodri on 4/2/2016.
 */
public class AccountsActivity extends Activity {

    int result;
    UtilFunctions util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts);

        Button newAccount = (Button) findViewById(R.id.btAddAccount);

        util = new UtilFunctions();



        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = util.checkKey(AccountsActivity.this);
                    }
                });

                t.start();

                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (result == 0) {
                    Intent newAccountIntent = new Intent(AccountsActivity.this, NewAccountActivity.class);
                    startActivity(newAccountIntent);
                }

            }
        });

    }

}

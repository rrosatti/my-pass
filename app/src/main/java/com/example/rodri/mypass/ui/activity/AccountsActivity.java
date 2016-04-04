package com.example.rodri.mypass.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rodri.mypass.R;
import com.example.rodri.mypass.account.Account;
import com.example.rodri.mypass.database.AccountsDataSource;
import com.example.rodri.mypass.ui.adapter.AdapterAccount;
import com.example.rodri.mypass.util.UtilFunctions;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rodri on 4/2/2016.
 */
public class AccountsActivity extends Activity {

    private AccountsDataSource dataSource;
    private AdapterAccount adapterAccount;

    int result;
    UtilFunctions util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts);

        dataSource = new AccountsDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Button newAccount = (Button) findViewById(R.id.btAddAccount);
        ListView allAccounts = (ListView)findViewById(R.id.listOfAccounts);

        List<Account> accounts = dataSource.getAllAccounts();



        if (accounts != null) {
            adapterAccount = new AdapterAccount(AccountsActivity.this, 0, accounts);
            allAccounts.setAdapter(adapterAccount);
        }


        util = new UtilFunctions();

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Thread t = new Thread(new Runnable() {
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
                 */

                // 0 - action referent to this Activity (start NewAccountActivity)
                result = util.checkKey(AccountsActivity.this, 0);

            }
        });

    }


}

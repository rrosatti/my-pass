package com.example.rodri.mypass.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodri.mypass.R;
import com.example.rodri.mypass.database.AccountsDataSource;
import com.example.rodri.mypass.util.EncryptDecrypt;

import java.sql.SQLException;

/**
 * Created by rodri on 4/2/2016.
 */
public class NewAccountActivity extends Activity {

    private AccountsDataSource dataSource;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);

        dataSource = new AccountsDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final EditText etAccountName = (EditText)findViewById(R.id.etAccountName);
        final EditText etLogin = (EditText)findViewById(R.id.etLogin);
        final EditText etPassword = (EditText)findViewById(R.id.etPassword);
        Button btConfirm = (Button)findViewById(R.id.btConfirm);

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName = etAccountName.getText().toString();
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();

                if (accountName.equals("")) {
                    Toast.makeText(NewAccountActivity.this, "You need to write the account name!", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (login.equals("")) {
                    Toast.makeText(NewAccountActivity.this, "You did not write the login...", Toast.LENGTH_LONG).show();
                    return;
                } else if (password.equals("")) {
                    Toast.makeText(NewAccountActivity.this, "You did not enter the password...", Toast.LENGTH_LONG).show();
                    return;
                }

                EncryptDecrypt enc = new EncryptDecrypt();
                String encPass = enc.encrypt(password);

                dataSource.createAccount(accountName, login, encPass);

                dataSource.close();

                Intent back = new Intent(NewAccountActivity.this, AccountsActivity.class);
                startActivity(back);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent back = new Intent(NewAccountActivity.this, AccountsActivity.class);
        startActivity(back);
        finish();
    }

}

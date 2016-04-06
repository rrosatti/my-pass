package com.example.rodri.mypass.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodri.mypass.R;
import com.example.rodri.mypass.account.Account;
import com.example.rodri.mypass.database.AccountsDataSource;

import java.sql.SQLException;

/**
 * Created by rodri on 4/2/2016.
 */
public class UtilFunctions {

    public void checkKey(Activity _activity, long _id) {
        final Activity activity = _activity;
        final long id = _id;

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_key, null);
        dialogBuilder.setView(dialogView);

        final EditText etPass = (EditText) dialogView.findViewById(R.id.etPass);

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EncryptDecrypt decrypt = new EncryptDecrypt();
                String pass = decrypt.getKey();

                if (!etPass.getText().toString().equals(pass)) {
                    Toast.makeText(activity, "The key is wrong", Toast.LENGTH_LONG).show();
                } else {
                    showPassword(activity, id);
                }

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = dialogBuilder.create();
        alert.show();


    }

    public void showPassword(Activity _activity, long _id) {
        final Activity activity = _activity;
        final long id = _id;

        AccountsDataSource dataSource = new AccountsDataSource(activity);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_show_password, null);
        dialogBuilder.setView(dialogView);
        Account account;
        EncryptDecrypt encDec = new EncryptDecrypt();
        String decryptedPass;

        final TextView txtShowPass = (TextView) dialogView.findViewById(R.id.txtShowPass);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        account = dataSource.getAccount(id);
        decryptedPass = encDec.decrypt(account.getPassword());

        txtShowPass.setText(decryptedPass);

        dataSource.close();

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = dialogBuilder.create();
        alert.show();

    }

}

package com.example.rodri.mypass.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodri.mypass.R;
import com.example.rodri.mypass.ui.activity.AccountsActivity;
import com.example.rodri.mypass.ui.activity.NewAccountActivity;
import com.example.rodri.mypass.util.EncryptDecrypt;

/**
 * Created by rodri on 4/2/2016.
 */
public class UtilFunctions {

    int result = -1;

    public int checkKey(final Activity _activity, int _action) {
        final Activity activity = _activity;
        final int action = _action;


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText etPass = (EditText) dialogView.findViewById(R.id.etPass);

        dialogBuilder.setTitle("KEY");
        dialogBuilder.setMessage("Insert the key: ");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EncryptDecrypt decrypt = new EncryptDecrypt();
                String pass = decrypt.getKey();

                if (!etPass.getText().toString().equals(pass)) {
                    result = 0;
                    Toast.makeText(activity, "The key is wrong", Toast.LENGTH_LONG).show();
                } else {
                    result = 1;
                    //Toast.makeText(activity, "Come in!", Toast.LENGTH_LONG).show();

                    switch (action) {
                        case (0): {
                            Intent nextScreen = new Intent(_activity, NewAccountActivity.class);
                            _activity.startActivity(nextScreen);
                            _activity.finish();
                        }
                        break;
                        case (1): {

                        }

                    }

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


        return result;

    }

}

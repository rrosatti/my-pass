package com.example.rodri.mypass.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodri.mypass.R;
import com.example.rodri.mypass.account.Account;
import com.example.rodri.mypass.database.AccountsDataSource;
import com.example.rodri.mypass.util.EncryptDecrypt;

import java.util.List;

/**
 * Created by rodri on 4/2/2016.
 */
public class AdapterAccount extends ArrayAdapter<Account> {

    private Activity activity;
    private List<Account> lAccount;
    private static LayoutInflater inflater = null;
    private AccountsDataSource dataSource;

    public AdapterAccount (Activity activity, int textViewResourceId, List<Account> lAccount) {
        super(activity, textViewResourceId, lAccount);
        try {
            this.activity = activity;
            this.lAccount = lAccount;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            // treat exception
        }
    }

    public int getCount() {
        return lAccount.size();
    }

    public static class ViewHolder {
        public TextView displayAccountName;
        public Button btGetPass;
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                v = inflater.inflate(R.layout.accounts_list_item, null);
                holder = new ViewHolder();

                holder.displayAccountName = (TextView) v.findViewById(R.id.showAccountName);
                holder.btGetPass = (Button) v.findViewById(R.id.btGetPass);

                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }

            holder.displayAccountName.setText(lAccount.get(position).getAccountName());

            holder.btGetPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UtilFunctions util = new UtilFunctions();

                    util.checkKey(activity, 1);

                }
            });

        } catch (Exception e) {
            Log.e("Error: ", "Error: " + e);
        }

        return v;
    }

}

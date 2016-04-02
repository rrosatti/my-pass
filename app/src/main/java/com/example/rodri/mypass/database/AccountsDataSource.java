package com.example.rodri.mypass.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rodri.mypass.account.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodri on 4/2/2016.
 */
public class AccountsDataSource {

    /**
     *  database fields
     */
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
                                  MySQLiteHelper.COLUMN_ACCOUNT_NAME,
                                  MySQLiteHelper.COLUMN_PASSWORD };

    public AccountsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Account createAccount(String accountName, String password) {
        String hashedPass = "";
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ACCOUNT_NAME, accountName);
        values.put(MySQLiteHelper.COLUMN_PASSWORD, password);

        long insertId = database.insert(MySQLiteHelper.TABLE_ACCOUNTS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ACCOUNTS, allColumns,
                                       MySQLiteHelper.COLUMN_ID + " = " +
                                       insertId, null, null, null ,null);

        cursor.moveToFirst();
        Account newAccount = cursorToAccount(cursor);
        cursor.close();
        return newAccount;
    }

    public Account cursorToAccount(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getLong(0));
        account.setAccountName(cursor.getString(1));
        account.setPassword(cursor.getString(2));
        return account;
    }

    public void deleteAccount(Account account) {
        long id = account.getId();
        System.out.println("The account with id " + id + " was removed!");
        database.delete(MySQLiteHelper.TABLE_ACCOUNTS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ACCOUNTS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Account account = cursorToAccount(cursor);
            accounts.add(account);
            cursor.moveToNext();
        }

        cursor.close();
        return accounts;
    }



}

package com.example.rodri.mypass.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rodri on 4/2/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACCOUNT_NAME = "account_name";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";

    public static final String DATABASE_NAME = "accounts.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_ACCOUNTS + "("
                                                    + COLUMN_ID + " integer primary key autoincrement, "
                                                    + COLUMN_ACCOUNT_NAME + " text not null, "
                                                    + COLUMN_LOGIN + " text not null, "
                                                    + COLUMN_PASSWORD + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
                                                + ", which will destroy all old data.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        onCreate(db);
    }

}

package com.sdcode.login_registration_ui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    private static final String myDb = "MyDb";
    private static final int version = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, myDb, null, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableAllUsers = "CREATE TABLE AllUsers(_id INTEGER PRIMARY KEY AUTOINCREMENT,FNAME VARCHAR(50),LNAME VARCHAR(50),GENDER VARCHAR(10), EMAIL VARCHAR(255),PHONE VARCHAR(255),PASSWORD VARCHAR(50))";
        db.execSQL(createTableAllUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

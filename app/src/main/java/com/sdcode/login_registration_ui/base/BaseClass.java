package com.sdcode.login_registration_ui.base;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.sdcode.login_registration_ui.database.DatabaseHelper;

public class BaseClass extends AppCompatActivity {
    protected DatabaseHelper helper;
    protected String name;
    protected long idName;

    protected void initData(){
        helper = new DatabaseHelper(getApplicationContext());

        SharedPreferences prefs = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        name = prefs.getString("email", "No email defined");
        idName = prefs.getLong("userId", 0);
    }

    protected void initUi(){

    }

}

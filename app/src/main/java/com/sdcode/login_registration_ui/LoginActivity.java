package com.sdcode.login_registration_ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sdcode.login_registration_ui.base.BaseClass;

public class LoginActivity extends BaseClass {
    MaterialButton btn_login, btn_register;
    TextInputLayout emailLayout, passwordLayout;
    TextInputEditText emailEditText, passwordEditText;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        initUi();
        initData();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username or passwords empty", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase database = helper.getReadableDatabase();

                    String[] columns = {"_id", "EMAIL", "PASSWORD"};
                    String[] cValues = {email, password};
                    Cursor cursor = database.query("AllUsers", columns, "EMAIL = ? AND PASSWORD = ?", cValues, null, null, null);
                    if (cursor.getCount() > 0) {

                        cursor.moveToFirst();

                        SharedPreferences.Editor editor = getSharedPreferences("LoginDetails", MODE_PRIVATE).edit();
                        editor.putString("email", email);
                        editor.putLong("userId", cursor.getLong(0));
                        editor.apply();

                        database.close();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Enter valid details!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationPage.class));
            }
        });
    }

    @Override
    protected void initUi() {
        super.initUi();

        getSupportActionBar().setTitle("Login");
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Toast.makeText(getApplicationContext(), "Login with your credentials!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
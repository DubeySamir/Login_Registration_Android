package com.sdcode.login_registration_ui;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sdcode.login_registration_ui.base.BaseClass;

public class RegistrationPage extends BaseClass {

    TextInputLayout firstNameLayout, lastNameLayout;
    TextInputEditText firstNameEditText, lastNameEditText;
    AppCompatSpinner genderAppCompatSpinner;
    EditText editTextPhone, editTextEmailAddress, editTextPassword, editTextConfirmPassword;
    Button signup_btn;

    String fName, lName, gender, phone, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        initUi();
        initData();
    }

    @Override
    protected void initUi() {
        super.initUi();

        getSupportActionBar().setTitle("Registration");
        firstNameLayout = findViewById(R.id.firstNameLayout);
        lastNameLayout = findViewById(R.id.lastNameLayout);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        genderAppCompatSpinner = findViewById(R.id.genderAppCompatSpinner);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);


        signup_btn = findViewById(R.id.signup_btn);


    }

    @Override
    protected void initData() {
        super.initData();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fName = firstNameEditText.getText().toString().trim();
                lName = lastNameEditText.getText().toString().trim();
                gender = genderAppCompatSpinner.getSelectedItem().toString().trim();
                phone = editTextPhone.getText().toString().trim();
                email = editTextEmailAddress.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                confirmPassword = editTextConfirmPassword.getText().toString().trim();
//Use Regular Expression
                if(fName.isEmpty() || lName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all Details", Toast.LENGTH_SHORT).show();
                }else {
                    if(!password.equals(confirmPassword)){
                        Toast.makeText(getApplicationContext(), "Password Not Match", Toast.LENGTH_SHORT).show();
                    }else{

                        boolean ans = checkUser(email, phone);

                        if(ans){
                            SQLiteDatabase database = helper.getWritableDatabase();
//"CREATE TABLE AllUsers(_id INTEGER PRIMARY KEY AUTOINCREMENT,FNAME VARCHAR(50),LNAME VARCHAR(50),GENDER VARCHAR(10), EMAIL VARCHAR(255),PHONE VARCHAR(255),PASSWORD VARCHAR(50))";
                            ContentValues values = new ContentValues();
                            values.put("FNAME", fName);
                            values.put("LNAME", lName);
                            values.put("GENDER", gender);
                            values.put("EMAIL", email);
                            values.put("PHONE", phone);
                            values.put("PASSWORD", password);

                            long user_id = database.insert("AllUsers", null, values);

                            user_id = Integer.parseInt(String.valueOf(user_id));

                            SharedPreferences.Editor editor = getSharedPreferences("LoginDetails", MODE_PRIVATE).edit();
                            editor.putString("email", email);
                            editor.putLong("userId", user_id);
                            editor.apply();

                            database.close();
                            Toast.makeText(getApplicationContext(), "Congratulations, You are a new Member", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "User Already Registered...", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });
    }

    private boolean checkUser(String email,String mono) {
//AllUsers(_id INTEGER PRIMARY KEY AUTOINCREMENT,FNAME VARCHAR(50),LNAME VARCHAR(50),GENDER VARCHAR(10), EMAIL VARCHAR(255),PHONE VARCHAR(255),PASSWORD VARCHAR(50))";
        final SQLiteDatabase database = helper.getReadableDatabase();

        String readData = "SELECT * FROM AllUsers";
        Cursor cursor = database.rawQuery(readData, null);

        boolean ans = true;
        if (!(cursor.getCount() == 0)) {
            cursor.moveToFirst();
            do {
                String getEmail = cursor.getString(4);
                String mobNo = cursor.getString(5);
                if (email.equals(getEmail) || mono.equals(mobNo)) {
                    ans = false;
                    return ans;
                }
            } while (cursor.moveToNext());
        }
        return ans;
    }
}
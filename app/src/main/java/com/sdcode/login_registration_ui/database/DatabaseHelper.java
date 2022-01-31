package com.sdcode.login_registration_ui.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sdcode.login_registration_ui.models.ModelClassRVUser;

import java.util.ArrayList;

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

    public ArrayList<ModelClassRVUser> getAllMaleUsersData(String gender) {

        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<ModelClassRVUser> objectModelClassList = new ArrayList<>();

        Cursor cursor = database.rawQuery("select * from AllUsers where GENDER like '" + gender + "'", null);

        if (cursor.getCount() != 0) {
            cursor.moveToPosition(-1);

            while (cursor.moveToNext()) {
                Integer userId = cursor.getInt(0);
                String genderLocal = cursor.getString(3);
                String userEmail = cursor.getString(4);
                String fName = cursor.getString(1);
                String lName = cursor.getString(2);
                String phone = cursor.getString(5);

                objectModelClassList.add(new ModelClassRVUser(userId, genderLocal, userEmail,fName,lName, phone));
            }
            return objectModelClassList;
        } else {
            Toast.makeText(context, "No values in database", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}

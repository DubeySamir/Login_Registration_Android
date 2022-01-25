package com.sdcode.login_registration_ui.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.sdcode.login_registration_ui.LoginActivity;
import com.sdcode.login_registration_ui.R;
import com.sdcode.login_registration_ui.base.BaseClass;
import com.sdcode.login_registration_ui.database.DatabaseHelper;

public class AccountFragment extends Fragment {

    TextView emailTV, fNameTV, lNameTV, genderTV, phoneTV;
    MaterialButton btn_logout;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);
        initUi(v);
        getSetData(v);
        return v;
    }

    private void getSetData(View v) {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        SharedPreferences prefs = getContext().getSharedPreferences("LoginDetails", MODE_PRIVATE);
        String email = prefs.getString("email", "No email defined");
        long userId = prefs.getLong("userId", 0);


//        String[] columns = {"_id", "EMAIL", "PASSWORD"};
        String[] columns = {"*"};
        String[] cValues = {String.valueOf(userId)};


        Cursor cursor = db.query("AllUsers", columns, "_id = ?", cValues, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            fNameTV.setText(cursor.getString(1));
            lNameTV.setText(cursor.getString(2));
            genderTV.setText(cursor.getString(3));
            emailTV.setText(cursor.getString(4));
            phoneTV.setText(cursor.getString(5));
            Log.v("User Found", DatabaseUtils.dumpCursorToString(cursor));
        } else {
            Log.v("User Not Found", DatabaseUtils.dumpCursorToString(cursor));
        }
    }

    private void initUi(View v) {
        emailTV = v.findViewById(R.id.emailTV);
        fNameTV = v.findViewById(R.id.fNameTV);
        lNameTV = v.findViewById(R.id.lNameTV);
        genderTV = v.findViewById(R.id.genderTV);
        phoneTV = v.findViewById(R.id.phoneTV);
        btn_logout = v.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getContext().getSharedPreferences("LoginDetails",MODE_PRIVATE);
                pref.edit().clear().apply();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
}
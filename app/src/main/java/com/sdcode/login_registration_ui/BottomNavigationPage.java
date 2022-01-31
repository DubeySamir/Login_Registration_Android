package com.sdcode.login_registration_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sdcode.login_registration_ui.fragments.AccountFragment;
import com.sdcode.login_registration_ui.fragments.FemaleUsersFragment;
import com.sdcode.login_registration_ui.fragments.MaleUsersFragment;

public class BottomNavigationPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_page);
        getSupportActionBar().setTitle("Bottom Navigation");

        getSupportFragmentManager().beginTransaction().add(R.id.frame,new MaleUsersFragment()).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);



        bottomNavigationView.setSelectedItemId(R.id.menu_maleUsers);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_maleUsers:
                        getSupportFragmentManager().beginTransaction().add(R.id.frame,new MaleUsersFragment()).commit();
                        return true;
                    case R.id.menu_femaleUsers:
                        getSupportFragmentManager().beginTransaction().add(R.id.frame,new FemaleUsersFragment()).commit();
                        return true;
                    case R.id.menu_account:
                        getSupportFragmentManager().beginTransaction().add(R.id.frame,new AccountFragment()).commit();
                        return true;
                }

                return false;
            }
        });
    }
}
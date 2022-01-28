package com.sdcode.login_registration_ui;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.sdcode.login_registration_ui.adapters.FragmentAdapter;
import com.sdcode.login_registration_ui.base.BaseClass;

import java.util.Objects;

public class MainActivity extends BaseClass {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home Page");

        initUi();
        initData();

        tabLayoutManager();
    }

    private void tabLayoutManager() {
        tabLayout.addTab(tabLayout.newTab().setText("Male Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Female Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Account"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    protected void initUi() {
        super.initUi();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
    }
    @Override
    protected void initData() {
        super.initData();

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fm,getLifecycle());
        viewPager.setAdapter(fragmentAdapter);
    }
}
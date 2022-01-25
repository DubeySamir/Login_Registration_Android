package com.sdcode.login_registration_ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sdcode.login_registration_ui.base.BaseClass;

public class SplashScreen extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initUi();
        initData();


        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(500);
                    if(idName != 0) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else{
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Interrupted Exception!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void initUi() {
        super.initUi();
    }
    @Override
    protected void initData() {
        super.initData();
    }


}
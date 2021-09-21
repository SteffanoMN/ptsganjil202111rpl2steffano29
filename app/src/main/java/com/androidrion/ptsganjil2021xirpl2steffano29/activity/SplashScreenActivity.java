package com.androidrion.ptsganjil2021xirpl2steffano29.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //menyetel splash screen selama 3 detik sebelum memulai mainactivity
        Thread background  = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(3 * 1000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                    finish();
                } catch (Exception e) {

                }
            }
        };
        background.start();
    }
}
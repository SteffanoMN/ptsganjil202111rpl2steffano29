package com.androidrion.ptsganjil2021xirpl2steffano29.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //ketika pengguna masuk, fragment yang akan ditampilkan adalah fragment home
        getFragmentPage(new Home());


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.page_1 :
                        fragment = new Home();
                        break;

                    case R.id.page_2 :
                        fragment = new Profile();
                        break;
                }

                return getFragmentPage(fragment);
            }
        });
    }

    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
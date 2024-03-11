package com.example.englishschedule.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishschedule.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppInforActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_infor);
        initBottomnavigation();
    }

    private void initBottomnavigation() {
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nvbappinfor);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nvbhome:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.nvbadd:
                        startActivity(new Intent(AppInforActivity.this, AddingActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.nvbreminder:
                        startActivity(new Intent(getApplicationContext(), ReminderActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.nvbappinfor:
                        return true;
                }
                return false;
            }
        });
    }
}

package com.example.englishschedule.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishschedule.R;
import com.example.englishschedule.adapter.ReminderListViewAdapter;
import com.example.englishschedule.asynctasks.AsynctaskForGetReminderToDo;
import com.example.englishschedule.asynctasks.AsynctaskForUpdateReminder;
import com.example.englishschedule.entities.LessonEntity;
import com.example.englishschedule.entities.ReminderEntity;
import com.example.englishschedule.model.Lesson;
import com.example.englishschedule.model.Reminder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    ListView listView;
    ReminderListViewAdapter adapter;
    ArrayList<Reminder> remindersToDo;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        initBottomnavigation();
        listView = findViewById(R.id.lvRM);
        initLisview();


    }

    private void initLisview(){
        try {
            AsynctaskForGetReminderToDo as = new AsynctaskForGetReminderToDo(this);
            remindersToDo = as.execute().get();
        }
        catch (Exception e){
            e.getMessage();
        }
        adapter = new ReminderListViewAdapter(this, remindersToDo);
        listView.setAdapter(adapter);
    }

    private void initBottomnavigation() {
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nvbreminder);
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
                        startActivity(new Intent(getApplicationContext(), AddingActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.nvbreminder:
                        return true;
                    case R.id.nvbappinfor:
                        startActivity(new Intent(getApplicationContext(), AppInforActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public void finishReminder(Reminder r) {
        AsynctaskForUpdateReminder as = new AsynctaskForUpdateReminder(this, r);
        as.execute();
        initLisview();
    }
}

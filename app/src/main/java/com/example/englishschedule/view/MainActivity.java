package com.example.englishschedule.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.englishschedule.R;
import com.example.englishschedule.adapter.HomeListViewAdapter;
import com.example.englishschedule.asynctasks.AsynctaskForSelectLesson;
import com.example.englishschedule.asynctasks.AsyntaskFromMainToLessInf;
import com.example.englishschedule.entities.LessonEntity;
import com.example.englishschedule.model.Lesson;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ArrayList<Lesson> lessons;
    private ListView listView;
    private HomeListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initBottomnavigation();
        listView = findViewById(R.id.lessonLVHOME);
        try {
            initListView();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private void initListView() throws Exception{
//        lessons =
        AsynctaskForSelectLesson as = new AsynctaskForSelectLesson(this);
        lessons = as.execute().get();
        adapter = new HomeListViewAdapter(this, lessons);
        listView.setAdapter(adapter);
    }

    private void initBottomnavigation() {
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nvbhome);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nvbhome:
                        return true;
                    case R.id.nvbadd:
                        startActivity(new Intent(MainActivity.this, AddingActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.nvbreminder:
                        startActivity(new Intent(getApplicationContext(), ReminderActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
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

    public void switchPage(int id) {
//        AsyntaskFromMainToLessInf as = new AsyntaskFromMainToLessInf(this, LessonInforActivity.class);
//        as.execute(id);
        Bundle bd = new Bundle();
        bd.putInt("ID", id);
        Intent it = new Intent(this, LessonInforActivity.class);
        it.putExtras(bd);
        startActivity(it);
    }
}
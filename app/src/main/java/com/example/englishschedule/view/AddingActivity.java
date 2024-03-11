package com.example.englishschedule.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishschedule.R;
import com.example.englishschedule.asynctasks.AsynctaskForAddLesson;
import com.example.englishschedule.entities.LessonEntity;
import com.example.englishschedule.model.Lesson;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class AddingActivity extends AppCompatActivity {
    Calendar c;
    TextView tvDate, tvDatePicker, tvtitle;
    EditText edtTitle;
    int idLesson;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding);
        initBottomnavigation();

        c = Calendar.getInstance();
        int day =  c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        idLesson = LessonEntity.selectNumberOfLesson(this)+1;
        tvtitle = findViewById(R.id.tvlsidADDING);
        edtTitle = findViewById(R.id.edttitleADDING);
        tvDate = findViewById(R.id.tvsdateADDING);
        tvDatePicker = findViewById(R.id.tvcalendarADDING);

        tvtitle.setText("Lesson " + idLesson +":");
        tvDate.setText(day+"/"+(month+1)+"/"+year);
        tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog picker = new DatePickerDialog(AddingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDate.setText(day+"/"+(month+1)+"/"+year);
                    }
                },year, month, day);
                picker.show();
            }
        });
    }

    private void initBottomnavigation() {
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nvbadd);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nvbhome:
                        startActivity(new Intent(AddingActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.nvbadd:

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

    public void addLesson(View view) {
        String title = edtTitle.getText().toString();
        if(title.equals("")){
            return;
        }
        else{
            Lesson lesson = new Lesson();
            lesson.setId(idLesson);
            lesson.setName(title);
            lesson.setStartDate(tvDate.getText().toString());
            AsynctaskForAddLesson as = new AsynctaskForAddLesson(this);
            try {
                boolean isAdded = as.execute(lesson).get();
                if(isAdded) {
                    navigationView.setSelectedItemId(R.id.nvbhome);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

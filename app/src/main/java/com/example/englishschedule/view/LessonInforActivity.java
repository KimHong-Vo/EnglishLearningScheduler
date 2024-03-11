package com.example.englishschedule.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishschedule.R;
import com.example.englishschedule.adapter.LsInfListVewAdapter;
import com.example.englishschedule.asynctasks.AsynctaskForAddWord;
import com.example.englishschedule.asynctasks.AsynctaskForGetLessonWithId;
import com.example.englishschedule.asynctasks.AsynctaskForSelectLesson;
import com.example.englishschedule.model.Lesson;

import java.util.concurrent.ExecutionException;

public class LessonInforActivity extends AppCompatActivity {
    private Lesson lesson;
    private int id;
    private LsInfListVewAdapter adapter;
    private ListView listView;
    private TextView tv;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_infor);
        id = getIntent().getExtras().getInt("ID");
        tv = findViewById(R.id.tvLSINFtitle);
        listView = findViewById(R.id.lvLSINF);
        initListView();
    }

    public void initListView(){
        try {
            AsynctaskForGetLessonWithId as = new AsynctaskForGetLessonWithId(this);
            lesson = as.execute(id).get();
            tv.setText("Lesson " + lesson.getId() + ": " + lesson.getName());
            if(lesson.getWords().size()>0) {
                adapter = new LsInfListVewAdapter(this, lesson.getWords());
                listView.setAdapter(adapter);
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addWord(View view) {
        EditText edt = findViewById(R.id.edtLSINF);
        if (edt.getText().toString().equals(""))
            return;
        else {
            AsynctaskForAddWord as = new AsynctaskForAddWord(this, lesson.getId(), edt.getText().toString());
            try{
                boolean isAdded = as.execute().get();
                if (isAdded) initListView();
                edt.setText("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void back(View view) {
        finish();
    }
}

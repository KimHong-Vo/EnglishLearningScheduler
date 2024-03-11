package com.example.englishschedule.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.englishschedule.R;
import com.example.englishschedule.entities.LessonEntity;
import com.example.englishschedule.model.Lesson;
import com.example.englishschedule.view.AddingActivity;

public class AsynctaskForAddLesson extends AsyncTask<Lesson, Void, Boolean> {
    private Activity context;
    public AsynctaskForAddLesson(Activity context){
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Lesson... lessons) {
        Lesson lesson = lessons[0];
        if( LessonEntity.insertLesson(lesson, context)){
            return true;
        }
        else
        return false;
    }
}

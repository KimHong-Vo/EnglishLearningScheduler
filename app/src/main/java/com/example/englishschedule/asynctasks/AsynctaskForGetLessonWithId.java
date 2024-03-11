package com.example.englishschedule.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.englishschedule.entities.LessonEntity;
import com.example.englishschedule.model.Lesson;

public class AsynctaskForGetLessonWithId extends AsyncTask<Integer, Void, Lesson> {
    private Activity activity;
    public AsynctaskForGetLessonWithId(Activity activity){
        this.activity = activity;
    }
    @Override
    protected Lesson doInBackground(Integer... integers) {
        int id = integers[0];
        return LessonEntity.selectLessonWithID(id, activity);
    }
}

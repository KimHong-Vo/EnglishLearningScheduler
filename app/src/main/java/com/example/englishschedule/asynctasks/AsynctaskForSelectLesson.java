package com.example.englishschedule.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.englishschedule.entities.LessonEntity;
import com.example.englishschedule.model.Lesson;

import java.util.ArrayList;

public class AsynctaskForSelectLesson extends AsyncTask<Void, Void, ArrayList<Lesson>> {
    private Activity content;

    public AsynctaskForSelectLesson(Activity content){
        this.content = content;
    }
    @Override
    protected ArrayList<Lesson> doInBackground(Void... voids) {
        return LessonEntity.selectLesson(content);
    }
}

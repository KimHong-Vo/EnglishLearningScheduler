package com.example.englishschedule.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.englishschedule.entities.WordEntity;
import com.example.englishschedule.model.Lesson;

public class AsynctaskForAddWord extends AsyncTask<Void, Void, Boolean> {
    private Activity activity;
    private int idLesson;
    private String word;
    public AsynctaskForAddWord(Activity activity, int idLesson, String word){
        this.activity = activity;
        this.idLesson = idLesson;
        this.word = word;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return WordEntity.insertWords(idLesson, word, activity);
    }
}

package com.example.englishschedule.asynctasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.englishschedule.view.LessonInforActivity;
import com.example.englishschedule.view.MainActivity;

public class AsyntaskFromMainToLessInf extends AsyncTask<Integer, Void, Void> {
    private Activity contextStart;
    private Class<LessonInforActivity> contextEnd;

    public AsyntaskFromMainToLessInf(Activity actStart, Class<LessonInforActivity> actEnd){
        this.contextStart = actStart;
        this.contextEnd = actEnd;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        Bundle bd = new Bundle();
        bd.putInt("ID", integers[0]);
        Intent it = new Intent(contextStart, contextEnd);
        it.putExtras(bd);
        contextStart.startActivity(it);
        return null;
    }
}

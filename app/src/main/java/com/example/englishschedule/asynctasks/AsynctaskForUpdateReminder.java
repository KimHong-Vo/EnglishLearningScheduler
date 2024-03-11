package com.example.englishschedule.asynctasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.example.englishschedule.entities.ReminderEntity;
import com.example.englishschedule.model.Reminder;

public class AsynctaskForUpdateReminder extends AsyncTask<Void, Void, Void> {
    private Activity context;
    private Reminder reminder;
    public AsynctaskForUpdateReminder(Activity context, Reminder reminder){
        this.reminder = reminder;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ReminderEntity.finishReminderToday(reminder, context);
        return null;
    }
}

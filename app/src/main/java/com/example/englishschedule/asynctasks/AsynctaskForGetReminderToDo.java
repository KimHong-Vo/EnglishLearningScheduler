package com.example.englishschedule.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.englishschedule.entities.ReminderEntity;
import com.example.englishschedule.model.Reminder;

import java.util.ArrayList;

public class AsynctaskForGetReminderToDo extends AsyncTask<Void, Void, ArrayList<Reminder>> {
    private Context context;

    public AsynctaskForGetReminderToDo(Context context){
        this.context = context;
    }
    @Override
    protected ArrayList<Reminder> doInBackground(Void... voids) {

        return ReminderEntity.getReminderToDo(context);
    }
}

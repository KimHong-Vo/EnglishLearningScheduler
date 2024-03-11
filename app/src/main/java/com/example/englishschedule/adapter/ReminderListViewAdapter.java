package com.example.englishschedule.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishschedule.R;
import com.example.englishschedule.entities.ReminderEntity;
import com.example.englishschedule.model.Lesson;
import com.example.englishschedule.model.Reminder;
import com.example.englishschedule.view.ReminderActivity;

import java.util.ArrayList;
import java.util.List;

public class
ReminderListViewAdapter extends ArrayAdapter<Reminder> {
    private ReminderActivity context;
    private List<Reminder> reminders;
    public ReminderListViewAdapter(@NonNull ReminderActivity context, @NonNull List<Reminder> objects) {
        super(context, 0, objects);
        reminders = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        convertView =inflater.inflate(R.layout.list_view_reminder, null);

        TextView tvTitle = convertView.findViewById(R.id.tvtitleLVRM);
        TextView tvLesson = convertView.findViewById(R.id.tvlessonLVRM);
        Button btnFinish = convertView.findViewById(R.id.btnFinishLVRM);

        Reminder r = reminders.get(position);
        tvLesson.setText("Lesson: " + r.getIdLesson() );
        tvTitle.setText(r.getPhase().getContent()+ " (" + r.getNumOfDayDone() + "/" + r.getPhase().getNumOfDoingDay() +")");
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(r.getPhase().getId()==0) {
                    Toast.makeText(context, "you're not alrealy add new lesson!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    context.finishReminder(r);
            }
        });

        return convertView;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }
}

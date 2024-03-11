package com.example.englishschedule.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishschedule.R;
import com.example.englishschedule.model.Lesson;
import com.example.englishschedule.model.Reminder;
import com.example.englishschedule.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeListViewAdapter extends ArrayAdapter<Lesson> {
    private List<Lesson> lessons;
    private MainActivity context;
    public HomeListViewAdapter(@NonNull MainActivity context, @NonNull List<Lesson> objects) {
        super(context, 0, objects);
        this.lessons = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        convertView =inflater.inflate(R.layout.list_view_home, null);
        Lesson lesson = lessons.get(position);

        LinearLayout layout = convertView.findViewById(R.id.layoutLinear);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.switchPage(lesson.getId());
            }
        });
        TextView tv = convertView.findViewById(R.id.tvLVHOMEtitle);
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add( convertView.findViewById(R.id.btnLVHOMEphase1));
        buttons.add( convertView.findViewById(R.id.btnLVHOMEphase2));
        buttons.add(convertView.findViewById(R.id.btnLVHOMEphase3));
        buttons.add( convertView.findViewById(R.id.btnLVHOMEphase4));
        buttons.add(convertView.findViewById(R.id.btnLVHOMEphase5));
        buttons.add(convertView.findViewById(R.id.btnLVHOMEphase6));

        tv.setText("Lesson " + lesson.getId() + ": " + lesson.getName());
        ArrayList<Reminder> reminders =lesson.getReminders();
        for(int i =0; i<reminders.size(); i++){
            if (reminders.get(i).isFinish())
                buttons.get(i).setBackgroundColor(Color.BLUE);
            else
                break;
        }
        return convertView;
    }
}

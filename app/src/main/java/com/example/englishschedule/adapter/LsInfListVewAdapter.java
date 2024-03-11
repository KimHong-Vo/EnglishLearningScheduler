package com.example.englishschedule.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishschedule.R;

import java.util.ArrayList;
import java.util.List;

public class LsInfListVewAdapter extends ArrayAdapter<String> {
    private Activity context;
    private List<String> words;
    public LsInfListVewAdapter(@NonNull Activity context,  @NonNull List<String> objects) {
        super(context, 0, objects);
        this.words = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        convertView =inflater.inflate(R.layout.list_view_lesson_infor, null);

        TextView tv = convertView.findViewById(R.id.tvLVLSINF);
        tv.setText((position+1) + ": " + words.get(position));
        return  convertView;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}

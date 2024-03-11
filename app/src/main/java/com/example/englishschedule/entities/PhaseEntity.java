package com.example.englishschedule.entities;

import android.content.Context;
import android.database.Cursor;

import com.example.englishschedule.database.Database;
import com.example.englishschedule.model.Phase;

public class PhaseEntity {


    public static Phase getPhase(int i, Context context) {
        Database db = new Database(context);
        Cursor c = db.getData(("SELECT * FROM phase WHERE id = " + i), null);
        if (c.moveToFirst()){
            Phase f = new Phase(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
            c.close();
            return  f;
        }
        return null;
    }
}

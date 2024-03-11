package com.example.englishschedule.entities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.englishschedule.database.Database;

import java.util.ArrayList;

public class WordEntity {

    public static boolean insertWords(int idlesson, String word, Context context){
        if (isHaveWord(idlesson, word, context))
            return false;
        ContentValues values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("word", word);
        Database db = new Database(context);
        return db.insert("word", values);
    }

    public static ArrayList<String> selectWordsWithIdLesson(int id, Context context) {
        ArrayList<String> words = new ArrayList<>();
        Database db = new Database(context);
        Cursor cursor = db.getData(("SELECT * FROM word WHERE idlesson = " + id), null);
        boolean ismore = cursor.moveToFirst();
        while (ismore){
            words.add(cursor.getString(1));
            ismore = cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return words;
    }

    public static boolean isHaveWord(int idLesson, String word, Context context){
        Database db = new Database(context);
        Cursor c = db.getData(("SELECT count(*) FROM word WHERE idlesson = " + idLesson +" AND word ='" + word+ "'"), null);
        c.moveToFirst();
        return c.getInt(0)>0;
    }
}

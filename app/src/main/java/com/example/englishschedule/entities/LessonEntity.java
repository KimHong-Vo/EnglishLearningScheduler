package com.example.englishschedule.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.englishschedule.database.Database;
import com.example.englishschedule.model.Lesson;
import com.example.englishschedule.model.Reminder;

import java.util.ArrayList;

public class LessonEntity {

    public static int selectNumberOfLesson(Context context){
        String script = "select count(*) from lesson";
        Database db = new Database(context);
        Cursor cursor = db.getData(script, null);
        cursor.moveToNext();
        int result =cursor.getInt(0);
        cursor.close();
        return result;

    }

    public static boolean insertLesson(Lesson lesson, Context context){
        ContentValues values = new ContentValues();
        values.put("id", lesson.getId());
        values.put("name", lesson.getName());
        values.put("startdate", lesson.getStartDate());
        values.put("isfinish", 0);

        Database db = new Database(context);
        if(db.insert("lesson", values) && ReminderEntity.insertReminder(lesson.getId(),lesson.getStartDate(), context))
            return true;
        return false;
    }

    public static Lesson selectLessonWithID(int id, Context context){
        Lesson lesson = new Lesson();
        Database db = new Database(context);
        Cursor cursor = db.getData(("SELECT * FROM lesson WHERE id = " + id), null);
        System.out.println("GET count: " + cursor.getCount());
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            lesson.setId(id);
            lesson.setName(cursor.getString(1));
            lesson.setStartDate(cursor.getString(2));
            lesson.setFinish(cursor.getInt(3) == 0 ? false : true);
            cursor.close();
            lesson.setWords(WordEntity.selectWordsWithIdLesson(id, context));
            db.close();
            return lesson;
        }
            else
                return null;
    }

    public static ArrayList<Lesson> selectLesson(Context context){
        ArrayList<Lesson> lessons = new ArrayList<>();
        String script = "SELECT * FROM lesson";
        Database db = new Database(context);
        Cursor cursor = db.getData(script, null);
        boolean ismore = cursor.moveToFirst();
        while (ismore){
            Lesson lesson = new Lesson();
            lesson.setId(cursor.getInt(0));
            lesson.setName(cursor.getString(1));
            lessons.add(lesson);
            ismore = cursor.moveToNext();
        }
        cursor.close();
        for(int i=0; i<lessons.size(); i++){
            lessons.get(i).setReminders(ReminderEntity.selectReminders(lessons.get(i).getId(), context));
        }
        db.close();
        return lessons;
    }

    public static boolean updateFinishLesson(int id, Context context){
        Database db = new Database(context);
        ContentValues values = new ContentValues();
        values.put("isfinish", 1);
        return  db.update("lesson", values, "id = ?" , new String[] {(""+id)});
    }
}

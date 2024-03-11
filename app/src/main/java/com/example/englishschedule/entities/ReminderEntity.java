package com.example.englishschedule.entities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.englishschedule.database.Database;
import com.example.englishschedule.model.Date;
import com.example.englishschedule.model.Lesson;
import com.example.englishschedule.model.Phase;
import com.example.englishschedule.model.Reminder;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderEntity {

    public static boolean insertReminder(int idlesson,String startLessonDate, Context context){
        try {

        Calendar c= Calendar.getInstance();
        int[] arrDate = Date.splitDate(startLessonDate);
        if (arrDate==null) return false;
        c.set(Calendar.DAY_OF_MONTH, arrDate[0]);
        c.set(Calendar.MONTH, (arrDate[1]-1));
        c.set(Calendar.YEAR, arrDate[2]);

        boolean isFinish = true;
        Database db = new Database(context);

        ContentValues values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("idphase", 1);// 6 phase 1-6 in database _ just remember_ dont need to select on

        String startdate = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        values.put("startdate", startdate);
        values.put("daydone", 0);
        db.insert("reminder",  values);

        c.add(Calendar.DATE, 3);//after 3 day of phase 1
        values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("idphase", 2);// 6 phase 1-6 in database _ just remember_ dont need to select on

        startdate = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        values.put("startdate", startdate);
        values.put("daydone", 0);
        db.insert("reminder",  values);

        c.add(Calendar.DAY_OF_MONTH, 5);// 2 day of phase 2 and 3 day space
        values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("idphase", 3);// 6 phase 1-6 in database _ just remember_ dont need to select on

        startdate = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        values.put("startdate", startdate);
        values.put("daydone", 0);
        db.insert("reminder",  values);

        c.add(Calendar.DAY_OF_MONTH, 5);//after space 5 day of phase 3
        values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("idphase", 4);// 6 phase 1-6 in database _ just remember_ dont need to select on

        startdate = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        values.put("startdate", startdate);
        values.put("daydone", 0);
        db.insert("reminder",  values);

        c.add(Calendar.DAY_OF_MONTH, 7);// after space 7 day from phase 4
        values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("idphase", 5);// 6 phase 1-6 in database _ just remember_ dont need to select on

        startdate = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        values.put("startdate", startdate);
        values.put("daydone", 0);
        db.insert("reminder",  values);

        c.add(Calendar.DAY_OF_MONTH, 30);
        values = new ContentValues();
        values.put("idlesson", idlesson);
        values.put("idphase", 6);// 6 phase 1-6 in database _ just remember_ dont need to select on

        startdate = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        values.put("startdate", startdate);
        values.put("daydone", 0);
        db.insert("reminder",  values);

        return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public static ArrayList<Reminder> getReminderToDo(Context context){
        ArrayList<Reminder> reminders = new ArrayList<>();
        Calendar c = Calendar.getInstance();

//        Get Reminder "addding new lesson"
        Reminder reminder = getAddLessonReminder(context);
        if (reminder != null)
            reminders.add(reminder);

//        Get Reminder "To do in lesson not finish"
        String today = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.YEAR);
        String script = "SELECT * FROM reminder join phase on reminder.idphase = phase.id" +
                " WHERE reminder.idlesson IN (SELECT id FROM lesson WHERE isfinish = 0) ";
        Database db = new Database(context);
        Cursor cursor = db.getData(script, null);
        boolean ismore = cursor.moveToFirst();
        while (ismore){
            if (!Date.isLaterOther(cursor.getString(2),today )){
                Reminder r = new Reminder();
                r.setIdLesson(cursor.getInt(0));
                r.setPhase(new Phase(cursor.getInt(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7)));
                r.setNumOfDayDone(cursor.getInt(3));
                r.setStartdate(cursor.getString(2));
                if (!r.isFinish()){
                reminders.add(r);}
               ismore=  cursor.moveToNext();
            }
            else
                ismore = cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return reminders;
    }

    public static ArrayList<Reminder> selectReminders(int idLesson, Context context){
        String script ="SELECT * FROM reminder join phase on reminder.idphase = phase.id WHERE reminder.idlesson = " + idLesson;
        ArrayList<Reminder> reminders = new ArrayList<>();
        Database db = new Database(context);
        Cursor cursor = db.getData(script, null);
        boolean ismore = cursor.moveToFirst();
        while (ismore){
            Phase phase = new Phase(cursor.getInt(4), cursor.getInt(7));
            Reminder r = new Reminder();
            r.setNumOfDayDone(cursor.getInt(3));
            r.setPhase(phase);
            reminders.add(r);
            ismore = cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return reminders;
    }

    public static void finishReminderToday(Reminder r, Activity context) {
        Database db = new Database(context);

        r.finishToday();
        String[] condition = new String[2];
        condition[0] = r.getIdLesson() +"";
        condition[1] = r.getPhase().getId() +"";

        ContentValues values = new ContentValues();
        values.put("startdate", r.getStartdate());
        values.put("daydone", r.getNumOfDayDone());

        db.update("reminder", values, "idlesson =? AND idphase = ?", condition);
        if (r.getPhase().getId()==6&&r.isFinish()){
            LessonEntity.updateFinishLesson(r.getIdLesson(), context);
        }
    }

    public static Reminder getAddLessonReminder(Context context){
        Database db = new Database(context);
        Cursor cursor = db.getData("SELECT * FROM lesson WHERE id = (SELECT MAX(id) FROM lesson)", null);
        if(cursor.moveToFirst()){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -5);
        if (!Date.isLaterOther(cursor.getString(2), (c.get(Calendar.DAY_OF_MONTH) +"/"
                +( c.get(Calendar.MONTH)+1)+ "/" + c.get(Calendar.YEAR)))){
            Reminder r = new Reminder();
            r.setIdLesson(cursor.getInt(0)+1);
            r.setPhase(PhaseEntity.getPhase(0, context));
            cursor.close();
            return r;
        }
        else {
            cursor.close();
            return  null;
        }

        }
        cursor.close();
        return null;
    }
}

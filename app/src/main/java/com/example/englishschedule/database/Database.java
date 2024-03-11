package com.example.englishschedule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "schedule";
    private static final int DB_VERSION = 3;
    public Database(@Nullable Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DB_VERSION);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String script = "CREATE TABLE IF NOT EXISTS lesson ( " +
                "id INTEGER NOT NULL primary key, name TEXT," +
                " startdate TEXT," + //d-m-y
                " isfinish INTEGER)";// 0 FALSE 1 TRUE
        sqLiteDatabase.execSQL(script);
        script = "CREATE TABLE IF NOT EXISTS word( " +
                "idlesson INTEGER NOT NULL, " +
                "word TEXT NOT NULL, " +
                "primary key(idlesson, word), " +
                "FOREIGN KEY(idlesson) REFERENCES lesson(id));";
        sqLiteDatabase.execSQL(script);
        script = "CREATE TABLE IF NOT EXISTS phase( " +
                "id INTEGER NOT NULL PRIMARY KEY, " +
                "context TEXT, space INTEGER, daydoing INTEGER); ";
        sqLiteDatabase.execSQL(script);
        script ="CREATE TABLE IF NOT EXISTS reminder( " +
                "idlesson INTEGER NOT NULL, " +
                "idphase INTEGER NOT NULL, " +
                "startdate TEXT NOT NULL, " +
                "daydone INTEGER, " + // true when daydone = daydoing
                "PRIMARY KEY(idlesson, idphase), " +
                "FOREIGN KEY(idlesson) REFERENCES lesson(id), " +
                "FOREIGN KEY(idphase) REFERENCES phase(id));";
        sqLiteDatabase.execSQL(script);

        ContentValues values = new ContentValues();
        values.put("id", 0);
        values.put("context", "Adding new lesson");
        values.put("space", 0);
        values.put("daydoing", 1);
        sqLiteDatabase.insert("phase", null, values);

        values.put("id", 1);
        values.put("context", "Learning new words");
        values.put("space", 0);
        values.put("daydoing", 3);
        sqLiteDatabase.insert("phase", null, values);

        values = new ContentValues();
        values.put("id", 2);
        values.put("context", "Learning relative words");
        values.put("space", 0);
        values.put("daydoing", 2);
        sqLiteDatabase.insert("phase", null, values);

        values = new ContentValues();
        values.put("id", 3);
        values.put("context", "Remind words + doing reading exercise");
        values.put("space", 3);
        values.put("daydoing", 1);
        sqLiteDatabase.insert("phase", null, values);

        values = new ContentValues();
        values.put("id", 4);
        values.put("context", "Remind words + doing listening exercise");
        values.put("space", 5);
        values.put("daydoing", 1);
        sqLiteDatabase.insert("phase", null, values);

        values = new ContentValues();
        values.put("id", 5);
        values.put("context", "Remind words + Listening");
        values.put("space", 7);
        values.put("daydoing", 1);
        sqLiteDatabase.insert("phase", null, values);

        values = new ContentValues();
        values.put("id", 6);
        values.put("context", "Remind words (level final)");
        values.put("space", 30);
        values.put("daydoing", 1);
        sqLiteDatabase.insert("phase", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insert(String table, ContentValues values){

        try {
            SQLiteDatabase db = getWritableDatabase();
            db.insert(table, null, values);
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    public boolean delete(String script, String[] args){
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(script, args);
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean update(String table,ContentValues content, String whereClause, String[] args){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if (db.update(table, content, whereClause, args)>0){
            System.out.println("Update success");
            db.close();
            return true;}
            else{
                System.out.println("Update fail");
                db.close();
                return false;
            }

        }
        catch (Exception e){
            return false;
        }
    }

    public Cursor getData(String script, String[] arg){
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(script, arg);
            return cursor;
        }
        catch (Exception e){
            return null;
        }
    }
}

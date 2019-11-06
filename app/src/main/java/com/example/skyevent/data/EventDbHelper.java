package com.example.skyevent.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = EventDbHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "SkyEvent.db";

    public static final int DATABASE_VERSION = 1;


    public EventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Event Entry
        String SQL_CREATE_EVENT_TABLE =  "CREATE TABLE " + DataContract.EventEntry.Table_Name + " ("
                + DataContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataContract.EventEntry.EventName + " TEXT NOT NULL, "
                + DataContract.EventEntry.Time_For_Event + " LONG NOT NULL); ";
        //people entry
        String SQL_CREATE_PEOPLE_LIST_TABLE = "CREATE TABLE "+ DataContract.PeopleEntry.Table_Name + "("+
                DataContract.PeopleEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DataContract.PeopleEntry.Name + " TEXT NOT NULL, " +
                DataContract.PeopleEntry.Phone + " LONG NOT NULL);";
        //task entry
        String SQL_CREATE_TASK_LIST_TABLE = "CREATE TABLE "+ DataContract.TaskEntry.Table_Name + "("+
                DataContract.TaskEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DataContract.TaskEntry.TaskDetails + " TEXT NOT NULL, " +
                DataContract.TaskEntry.TaskDate + " TEXT NOT NULL, " +
                DataContract.TaskEntry.TaskStatus + " TEXT NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_EVENT_TABLE);
        db.execSQL(SQL_CREATE_PEOPLE_LIST_TABLE);
        db.execSQL(SQL_CREATE_TASK_LIST_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

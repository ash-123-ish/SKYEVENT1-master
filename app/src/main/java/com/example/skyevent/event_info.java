package com.example.skyevent;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skyevent.data.DataContract.EventEntry;
import com.example.skyevent.data.EventDbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class event_info extends AppCompatActivity {

    EditText mname,mdate,mmonth,myear,mhour,mminutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        EventDbHelper dataHelper=new EventDbHelper(this);
        SQLiteDatabase database=dataHelper.getReadableDatabase();

        Cursor cur=database.rawQuery("Select * From "+EventEntry.Table_Name,null);

        if(cur.getCount()>0)
        {
            cur.close();
            Intent intent=new Intent(this,home.class);
            startActivity(intent);
        }

        //**************************************************************
        mname=findViewById(R.id.ename);
        mdate=findViewById(R.id.edate);
        mmonth=findViewById(R.id.emonth);
        myear=findViewById(R.id.eyear);
        mhour=findViewById(R.id.ehour);
        mminutes=findViewById(R.id.eminutes);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       Context context;
        MenuInflater inflater= getMenuInflater();
       inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.share)
        {
            Toast.makeText(this, "you select share", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.about) {
            Toast.makeText(this, "you selected about", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.exit) {
            Toast.makeText(this, "you selected exit" , Toast.LENGTH_SHORT).show();
        }else if(id == R.id.save) {
            if(mname.getText().toString().equals("") || mdate.getText().toString().equals("") || mmonth.getText().toString().equals("") || myear.getText().toString().equals("") || mhour.getText().toString().equals("") || mminutes.getText().toString().equals(""))
            {
                Toast.makeText(this,"you never keep the blank",Toast.LENGTH_SHORT).show();
            }
            else
            {
                String name=mname.getText().toString();
                String edate=mdate.getText().toString();
                int date=Integer.parseInt(edate);
                String month=mmonth.getText().toString();
                String eyear=myear.getText().toString();
                int year=Integer.parseInt(eyear);
                String ehour=mhour.getText().toString();
                int hour=Integer.parseInt(ehour);
                String eminutes=mminutes.getText().toString();
                int minutes=Integer.parseInt(eminutes);

                String a=month + " " + date + " " + year + " " + hour + ":" + minutes + ":00.000 GMT";

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

                long time_in_millisecond = 0;
                try {
                    Date date1=simpleDateFormat.parse(a);
                    time_in_millisecond= date1.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date today = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat=new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

                String currentTime= dateFormat.format(today);

                long todayTimeInMilli = 0;

                try
                {
                    Date date2=simpleDateFormat.parse(currentTime);
                    todayTimeInMilli=date2.getTime();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                if(todayTimeInMilli>time_in_millisecond)
                {
                    Toast.makeText(event_info.this, "Date should be greater than today's date", Toast.LENGTH_SHORT).show();
                }
                else {
                    insertEvent(name, time_in_millisecond);
                    Intent intent=new Intent(event_info.this,home.class);
                    startActivity(intent);
                }

            }

        }
        return true;
    }


    private void insertEvent(String name, long eventTimeInMillisecond)
    {
        EventDbHelper mdbHelper=new EventDbHelper(this);

        SQLiteDatabase db= mdbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(EventEntry.EventName,name);
        values.put(EventEntry.Time_For_Event,eventTimeInMillisecond);

        long row_ID= db.insert(EventEntry.Table_Name,null,values);

        if(row_ID == -1)
        {
            Toast.makeText(this,"Data is not inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Your data is inserted.",Toast.LENGTH_SHORT).show();

        }

    }
}

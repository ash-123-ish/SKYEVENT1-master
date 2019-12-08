package com.example.skyevent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyevent.data.DataContract;
import com.example.skyevent.data.EventDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class home extends AppCompatActivity {
TextView eventnametext,countdowntimetext;
    long timeLeftInMillisecond;
    NotificationHelper notificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button b1=(Button) findViewById(R.id.TaskList);
        Button b2=(Button) findViewById(R.id.guestList);
        Button b3=(Button) findViewById(R.id.budget);
        Button b4=(Button) findViewById(R.id.add_event);

        eventnametext=findViewById(R.id.eventName);
        countdowntimetext=findViewById(R.id.countdownText);

        notificationHelper= new NotificationHelper(this);

        FloatingActionButton fabtn=(FloatingActionButton) findViewById(R.id.fab);
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("smsto"+"");
                Intent i=new Intent(Intent.ACTION_SENDTO,uri);
                i.setPackage("com.whatsapp");
                startActivity(i);
            }
        });

        EventDbHelper dbhelper=new EventDbHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        Cursor cursor=db.rawQuery(" Select * From "+ DataContract.EventEntry.Table_Name,null);
        String event_name = null;
        long time=0;
        while(cursor.moveToNext())
        {
            int columnindex=cursor.getColumnIndex(DataContract.EventEntry.EventName);
            event_name=cursor.getString(columnindex);
            int columnindex2=cursor.getColumnIndex(DataContract.EventEntry.Time_For_Event);
            time=cursor.getLong(columnindex2);
        }
        cursor.close();

        eventnametext.setText(event_name);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

        String currentTime= simpleDateFormat.format(today);

        long todayTimeInMilli = 0;

        try
        {
            Date date=simpleDateFormat.parse(currentTime);
            todayTimeInMilli=date.getTime();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        timeLeftInMillisecond= time - todayTimeInMilli;
        //create timer and notification from here...
        CountDownTimer countDownTimer=new CountDownTimer(timeLeftInMillisecond,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillisecond = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }
    private long backPressedTime;
    @Override
    public void onBackPressed() {
        if(backPressedTime +2000 > System.currentTimeMillis())
        {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else {
            Toast.makeText(this,"Press back to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }


    public void updateTimer()
    {
        int hours=(int) timeLeftInMillisecond/3600000;
        int minutes=(int) timeLeftInMillisecond % 3600000 /60000;
        int seconds=(int) timeLeftInMillisecond % 60000 / 1000;

        //if(minutes == 9 && seconds==0)
        if((hours==0 && minutes == 30 && seconds==0) || (hours==0 && minutes == 10 && seconds==0) || (hours==0 && minutes==5 && seconds==0) || (hours==1 && minutes==0 && seconds==0) || (hours==6 && minutes==0 && seconds==0))
        {
            if(hours<1) {
                String time = minutes + " minute";
                sendNotification(("You have Only! ").toString()+time.toString(),"Hurry up! No task should be left");
            }
            else
            {
                String time = hours + " hour";
                sendNotification(("you have only! ").toString()+time,"See, No task should be left");
            }
        }

        if(timeLeftInMillisecond < 1000)
        {
            countdowntimetext.setText("00:00:00");
            sendNotification("your event is Started!","");
        }
        String timeLeftText = null;
        if(hours<10) {
            timeLeftText = "0";
            timeLeftText += hours;
        }
        else
        {
            timeLeftText = "" + hours;
        }

        timeLeftText += ":";
        if(minutes<10)
        {
            timeLeftText +="0";
        }
        timeLeftText += minutes;
        timeLeftText += ":";
        if(seconds<10)
            timeLeftText+="0";

        timeLeftText+=seconds;

        countdowntimetext.setText(timeLeftText);
    }

    public void sendNotification(String title, String message)
    {
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(title, message);
        notificationHelper.getManager().notify(1,nb.build());
    }



    public void removeEvent(View view) {
        EventDbHelper mdbhelper=new EventDbHelper(this);
        SQLiteDatabase db=mdbhelper.getWritableDatabase();

        db.execSQL("DELETE FROM "+DataContract.EventEntry.Table_Name);
        db.execSQL("DELETE FROM "+DataContract.BudgetEntry.Table_Name);
        db.execSQL("DELETE FROM "+DataContract.PeopleEntry.Table_Name);
        db.execSQL("DELETE FROM "+DataContract.TaskEntry.Table_Name);

        Context context;
        CharSequence text;
        Toast.makeText(this, "you deleted this event", Toast.LENGTH_SHORT).show();

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

        //Intent intent=new Intent(home.this,event_info.class);
        //startActivity(intent);
    }

    public void budgetIntent(View view) {
        Intent intent=new Intent(home.this, budget.class);
        startActivity(intent);
    }

    public void guestIntent(View view) {
        Intent intent = new Intent(home.this,guest.class);
        startActivity(intent);
    }

    public void task(View view) {
        Intent intent=new Intent(home.this,task.class);
        startActivity(intent);
    }


}

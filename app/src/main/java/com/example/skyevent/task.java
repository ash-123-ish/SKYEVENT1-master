package com.example.skyevent;

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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.skyevent.data.DataContract;
import com.example.skyevent.data.EventDbHelper;

import java.util.ArrayList;

public class task extends AppCompatActivity {

    int a;
    Cursor cursor;
    ArrayList<TaskModel> tlist;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        ListView listView=(ListView) findViewById(R.id.taskList);

        EventDbHelper dbhelper=new EventDbHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        cursor=db.rawQuery("Select * From "+ DataContract.TaskEntry.Table_Name,null);

        a=cursor.getCount();

        tlist= new ArrayList<>();
       // TaskModel tm=new TaskModel();
        if(cursor.moveToFirst())
        {
            do {
                int columnindex=cursor.getColumnIndex(DataContract.TaskEntry.TaskDetails);
                String taskdetails=cursor.getString(columnindex);
                //tm.setDetail(taskdetails);
                int columnindex1=cursor.getColumnIndex(DataContract.TaskEntry.TaskDate);
                String taskdate=cursor.getString(columnindex1);
               // tm.setDate(taskdate);
                int columnindex2=cursor.getColumnIndex(DataContract.TaskEntry.TaskStatus);
                String taskstatus=cursor.getString(columnindex2);
               // tm.setStatus(taskstatus);

                TaskModel taskModel=new TaskModel(taskdetails,taskstatus,taskdate);
               // list.add(tm);
                tlist.add(taskModel);
            }while (cursor.moveToNext());


        }
        else
        {
            cursor.close();
        }

        Context context;

        TaskListAdapter adapter=new TaskListAdapter(this,R.layout.element,tlist);

        listView.setAdapter(adapter);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,home.class);
        startActivity(intent);
    }

    public void insertTask(View view) {
        Intent intent =new Intent(task.this,task_info.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Context context;
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.back) {
            Intent intent=new Intent(this,home.class);
            startActivity(intent);
        }
        return true;
    }

}

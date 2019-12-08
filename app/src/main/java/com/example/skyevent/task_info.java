package com.example.skyevent;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.skyevent.data.DataContract;
import com.example.skyevent.data.EventDbHelper;

public class task_info extends AppCompatActivity {
// boolean status = false;
String status= "Pending";
 EditText tdetail,tdate,tmonth,tyear;
 ToggleButton tstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        tdetail=findViewById(R.id.tdetails);
        tstatus=findViewById(R.id.tstatus);
        tdate=findViewById(R.id.tdate);
        tmonth=findViewById(R.id.tmonth);
        tyear=findViewById(R.id.tyear);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,task.class);
        startActivity(intent);
    }

    private void insertEvent(String details, String status, String date)
    {
        EventDbHelper mdbHelper=new EventDbHelper(this);

        SQLiteDatabase db= mdbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(DataContract.TaskEntry.TaskDetails,details);
        values.put(DataContract.TaskEntry.TaskDate,date);
        values.put(DataContract.TaskEntry.TaskStatus,status);

        long row_ID= db.insert(DataContract.TaskEntry.Table_Name,null,values);

        if(row_ID == -1)
        {
            Toast.makeText(this,"Data is not inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Your data is inserted.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save)
        {
            if(tdetail.getText().toString().equals("") || tdate.getText().toString().equals("") || tmonth.getText().toString().equals("") || tyear.getText().toString().equals(""))
            {
                Toast.makeText(task_info.this,"you cannot leave blank here",Toast.LENGTH_SHORT);
            }
            else
            {
                String detail=tdetail.getText().toString();
                String date = tdate.getText().toString();
                date+="/";
                date+=tmonth.getText().toString();
                date+="/";
                date+=tyear.getText().toString();

                tstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            status ="Complete";
                            Toast.makeText(task_info.this, "status is completed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            status ="Pending";
                            Toast.makeText(task_info.this,"status is pending",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                insertEvent(detail, status, date);
               Intent intent = new Intent(task_info.this, task.class);
                startActivity(intent);
            }
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Context context;
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return  true;
    }

    public void insertTask(View view) {
        Intent intent=new Intent(this,home.class);
        startActivity(intent);
    }
}

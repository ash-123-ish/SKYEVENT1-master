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

import com.example.skyevent.data.DataContract.PeopleEntry;
import com.example.skyevent.data.EventDbHelper;

public class guest_info extends AppCompatActivity {

    String status="Unmarked";
    EditText name,phone;
    ToggleButton tstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_info);
        name=findViewById(R.id.people_name);
        phone=findViewById(R.id.people_phone);
        tstatus=findViewById(R.id.people_status);



        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,guest.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Context context;
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.save)
        {
            String Pname=name.getText().toString();

            String Pphone=phone.getText().toString();

            //long PphoneNo=Long.parseLong(Pphone);

            tstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        status = "Marked";
                        Toast.makeText(guest_info.this, "status is marked", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        status = "Unmarked";
                        Toast.makeText(guest_info.this,"status is unmarked",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            if(Pname.trim().equals("") || Pphone.trim().equals(""))
            {
                Toast.makeText(this, "Please fill all the data", Toast.LENGTH_SHORT).show();
            }
            else {
                insertValues(Pname.trim(), Pphone, status);
                Intent intent = new Intent(guest_info.this, guest.class);
                startActivity(intent);
            }
        }

        return true;
    }


    private void insertValues(String Pname, String Pphone, String status)
    {
        EventDbHelper mdbHelper=new EventDbHelper(this);

        SQLiteDatabase db= mdbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(PeopleEntry.Name,Pname);
        values.put(PeopleEntry.Phone,Pphone);
        values.put(PeopleEntry.Status,status);

        long row_ID= db.insert(PeopleEntry.Table_Name,null,values);

        if(row_ID == -1)
        {
            Toast.makeText(this,"Data is not inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"your data is inserted.",Toast.LENGTH_SHORT).show();
        }
    }

    public void insertPeople(View view) {
        Intent intent=new Intent(this,home.class);
        startActivity(intent);
    }
}

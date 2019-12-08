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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.skyevent.data.DataContract;
import com.example.skyevent.data.EventDbHelper;

public class budget_info extends AppCompatActivity {
    Button b1,b2;
    EditText bdetail,bdate,bmonth,byear,bcost;
    ToggleButton bstatus;
    String status="Pending";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_info);

         bdetail = findViewById(R.id.bdetails);
        bstatus=findViewById(R.id.bstatus);
        bdate = findViewById(R.id.bdate);
        bmonth=findViewById(R.id.bmonth);
        byear=findViewById(R.id.byear);
        bcost=findViewById(R.id.bcost);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Context context;
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save)
        {
           // String detail=
            if(bdetail.getText().toString().equals("") || bdate.getText().toString().equals("") || bmonth.getText().toString().equals("") || byear.getText().toString().equals(""))
            {
                Toast.makeText(budget_info.this,"you cannot leave blank here",Toast.LENGTH_SHORT);
            }
            else
            {
                String detail=bdetail.getText().toString();
                String date = bdate.getText().toString();
                date+="/";
                date+=bmonth.getText().toString();
                date+="/";
                date+=byear.getText().toString();

                bstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            status ="Complete";
                            Toast.makeText(budget_info.this, "status is completed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            status ="Pending";
                            Toast.makeText(budget_info.this,"status is pending",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                String cost=bcost.getText().toString();

                insertEvent(detail, status, date, cost);
                Intent intent = new Intent(budget_info.this, budget.class);
                startActivity(intent);
            }
        }

        return true;
    }

    private void insertEvent(String detail, String status, String date, String cost) {

        EventDbHelper mdbHelper=new EventDbHelper(this);

        SQLiteDatabase db= mdbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(DataContract.BudgetEntry.BudgetName,detail);
        values.put(DataContract.BudgetEntry.BudgetDate,date);
        values.put(DataContract.BudgetEntry.BudgetStatus,status);
        values.put(DataContract.BudgetEntry.BudgetCost,cost);

        long row_ID= db.insert(DataContract.BudgetEntry.Table_Name,null,values);

        if(row_ID == -1)
        {
            Toast.makeText(this,"Data is not inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Your data is inserted.",Toast.LENGTH_SHORT).show();
        }
    }

    public void insertTask(View view) {
        Intent intent=new Intent(this,home.class);
        startActivity(intent);
    }
}

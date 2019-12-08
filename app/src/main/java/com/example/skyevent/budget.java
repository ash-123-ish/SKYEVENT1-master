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

public class budget extends AppCompatActivity {

    int a;
    Cursor cursor;
    ArrayList<budgetModel> blist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        ListView listView=(ListView) findViewById(R.id.budgetList);

        EventDbHelper dbhelper=new EventDbHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor=db.rawQuery("Select * From "+ DataContract.BudgetEntry.Table_Name,null);

        a=cursor.getCount();
        blist= new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do {
                int columnindex=cursor.getColumnIndex(DataContract.BudgetEntry.BudgetName);
                String budgetdetails=cursor.getString(columnindex);
                int columnindex1=cursor.getColumnIndex(DataContract.BudgetEntry.BudgetDate);
                String budgetdate=cursor.getString(columnindex1);
                int columnindex2=cursor.getColumnIndex(DataContract.BudgetEntry.BudgetStatus);
                String budgetstatus=cursor.getString(columnindex2);
                int columnindex3=cursor.getColumnIndex(DataContract.BudgetEntry.BudgetCost);
                String budgetcost=cursor.getString(columnindex3);

                budgetModel budgetModel=new budgetModel(budgetdetails,budgetstatus,budgetdate,budgetcost);

                blist.add(budgetModel);
            }while (cursor.moveToNext());
        }
        else
        {
            cursor.close();
        }

        Context context;

        BudgetListAdapter adapter=new BudgetListAdapter(this,R.layout.elemento,blist);

        listView.setAdapter(adapter);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void insertTask(View view) {
        Intent intent=new Intent(this,budget_info.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,home.class);
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

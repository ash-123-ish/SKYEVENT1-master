package com.example.skyevent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skyevent.data.DataContract;
import com.example.skyevent.data.EventDbHelper;

import java.util.ArrayList;

public class guest extends AppCompatActivity {

    Cursor cursor;
    int index=0;
    int a;
    ArrayList<guestModel>  gdetail;

    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        final ListView listView=(ListView) findViewById(R.id.guestlist);

       FloatingActionButton add=(FloatingActionButton) findViewById(R.id.addGuest);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(guest.this,guest_info.class);
                startActivity(intent);
            }
        });


        EventDbHelper dbhelper=new EventDbHelper(this);
        final SQLiteDatabase db = dbhelper.getReadableDatabase();

        cursor=db.rawQuery("Select * From "+ DataContract.PeopleEntry.Table_Name,null);


        gdetail=new ArrayList<>();


        if(cursor.moveToFirst()) {
            do {
                int columnindex = cursor.getColumnIndex(DataContract.PeopleEntry.Name);
                String guestname = cursor.getString(columnindex);


                int columnindex1 = cursor.getColumnIndex(DataContract.PeopleEntry.Phone);
                long guestphone = cursor.getLong(columnindex1);


                int columnindex2=cursor.getColumnIndex(DataContract.PeopleEntry.Status);
                String gueststatus=cursor.getString(columnindex2);

                guestModel guestmodel =new guestModel(guestname,guestphone,gueststatus);

                gdetail.add(guestmodel);
            }while (cursor.moveToNext());

        }
        else
        {
            cursor.close();
        }

        Context context;
        PersonListAdapter adapter=new PersonListAdapter(this,R.layout.custom_guest_list_layout,gdetail);

        listView.setAdapter(adapter);


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int index=position;

                //int id=listView.getItemIdAtPosition(index);
                System.out.println(id);
                cursor=db.rawQuery("Select Phone From "+DataContract.PeopleEntry.Table_Name + " where _ID = " + (id+1),null);
                cursor.moveToFirst();
                int colindex = cursor.getColumnIndex(DataContract.PeopleEntry.Phone);
                String phone = cursor.getString(colindex+1);
                cursor.close();
               // String phon=String.valueOf(phone);

                Uri u=Uri.parse("tel:"+ phone);
                Intent i=new Intent(Intent.ACTION_DIAL,u);
                try
                {
                    startActivity(i);
                }
                catch(SecurityException e)
                {
                    Toast.makeText(guest.this,"Exception generated",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,home.class);
        startActivity(intent);
    }

    public void insertPeople(View view) {
        Intent intent=new Intent(guest.this,guest_info.class);
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

package com.example.skyevent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<guestModel> {

    private Context mcontext;
    int mResource;
    public PersonListAdapter(@NonNull Context context, int resource, ArrayList<guestModel> objects) {
        super(context, resource, objects);
        mcontext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name=getItem(position).getGuestName();
        long phone= getItem(position).getGuestPhone();

        guestModel model =new guestModel(name,phone);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvname =convertView.findViewById(R.id.guestName);
        TextView tvphone =convertView.findViewById(R.id.guestDescription);
        tvname.setText(name);
        tvphone.setText(""+phone);
        return convertView;
    }
}

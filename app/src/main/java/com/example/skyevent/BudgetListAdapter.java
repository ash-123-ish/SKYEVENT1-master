package com.example.skyevent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class BudgetListAdapter extends ArrayAdapter<budgetModel> {

    private Context mcontext;
    int mResource;

    public BudgetListAdapter(@NonNull Context context, int resource, @NonNull List<budgetModel> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name=getItem(position).getName();
        String status=getItem(position).getStatus();
        String date=getItem(position).getDate();
        String cost=getItem(position).getCost();

        budgetModel model =new budgetModel(name,status,date,cost);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvname =convertView.findViewById(R.id.TaskName);
        TextView tvstatus =convertView.findViewById(R.id.TaskStatus);
        TextView tvdate =convertView.findViewById(R.id.TaskDate);
        TextView tvcost =convertView.findViewById(R.id.TaskCost);

        tvname.setText(name);
        tvstatus.setText(status);
        tvdate.setText(date);
        tvcost.setText(cost);


        return convertView;
    }

}

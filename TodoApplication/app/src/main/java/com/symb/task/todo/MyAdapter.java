package com.symb.task.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Person> pList;
    private static LayoutInflater inflater = null;


    public MyAdapter(Context context, ArrayList<Person> empList) {
        this.context = context;
        this.pList = empList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }





    @Override
    public int getCount() {
        return pList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = inflater.inflate(R.layout.layout_grid_item,null);

        TextView personName = convertView.findViewById(R.id.personName);
        TextView date = convertView.findViewById(R.id.date);

        Person p =new Person();
        p=pList.get(position);
        personName.setText(""+String.valueOf(p.getPersonName()));
        date.setText(""+String.valueOf(p.getDate()));
        return convertView;
    }
}

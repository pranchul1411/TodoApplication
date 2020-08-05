package com.symb.task.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Person> personArrayList;
    MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        gridView = (GridView) findViewById(R.id.todo_grid);
        MyDbHelper mHelper = new MyDbHelper(this);
        personArrayList = new ArrayList<Person>();

        personArrayList = mHelper.getList();
        adapter = new MyAdapter(this,personArrayList);
        gridView.setAdapter(adapter);
    }

}

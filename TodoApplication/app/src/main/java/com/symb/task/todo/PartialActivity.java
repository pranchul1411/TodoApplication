package com.symb.task.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PartialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial);

        Button createTodo,todoList;

        createTodo = findViewById(R.id.createTodo);
        todoList = findViewById(R.id.todoList);

        createTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PartialActivity.this,MainActivity.class));
            }
        });

        todoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PartialActivity.this, TodoListActivity.class));
            }
        });
    }
}

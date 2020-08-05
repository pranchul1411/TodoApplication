package com.symb.task.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText mText;
    Button mAdd;


    MyDbHelper mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText)findViewById(R.id.name);
        mAdd = (Button)findViewById(R.id.add);
        mAdd.setOnClickListener(this);

        mHelper = new MyDbHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDb = mHelper.getWritableDatabase();
        String[] columns = new String[] {"_id", MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
        mCursor = mDb.query(MyDbHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        String[] headers = new String[] {MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
  }

    @Override
    public void onPause() {
        super.onPause();
        mDb.close();
        mCursor.close();
    }
    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues(2);
        cv.put(MyDbHelper.COL_NAME, mText.getText().toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cv.put(MyDbHelper.COL_DATE, dateFormat.format(new Date())); //Insert 'now' as the date
        mDb.insert(MyDbHelper.TABLE_NAME, null, cv);
        mCursor.requery();
        mText.setText(null);
        Intent intent = new Intent(MainActivity.this,PartialActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        mCursor.moveToPosition(position);
        String rowId = mCursor.getString(0); //Column 0 of the cursor is the id
        mDb.delete(MyDbHelper.TABLE_NAME, "_id = ?", new String[]{rowId});
        mCursor.requery();
       }
}
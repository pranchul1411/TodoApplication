package com.symb.task.todo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class  MyDbHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = "mydb";
        private static final int DB_VERSION = 1;

        //Create Table
        public static final String TABLE_NAME = "student";
        public static final String COL_NAME = "pName";
        public static final String COL_DATE = "pDate";
        private static final String STRING_CREATE = "CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_NAME+" TEXT, "+COL_DATE+" DATE);";

        public MyDbHelper(Context context) {
                super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                //Execute Query
                db.execSQL(STRING_CREATE);
                db.execSQL("CREATE VIEW ViewList AS SELECT "+TABLE_NAME+"._id AS _id,"+TABLE_NAME+"."+COL_NAME+" as COL_NAME, "+TABLE_NAME+"."+COL_DATE+" as COL_DATE FROM "+TABLE_NAME);
                ContentValues cv = new ContentValues(2);
                cv.put(COL_NAME, "New Entry");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                cv.put(COL_DATE, dateFormat.format(new Date()));
                db.insert(TABLE_NAME, null, cv);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
                onCreate(db);
        }

        public  ArrayList<Person> getList() {
                //Show Data
                String query = "SELECT * FROM " + TABLE_NAME;
                ArrayList<Person> employees = new ArrayList<Person>();
                SQLiteDatabase database = getReadableDatabase();
                Cursor c = database.rawQuery(query, null);
                if (c != null) {
                        while (c.moveToNext()) {
                                String personName = c.getString(1);
                                String date = c.getString(2);

                                Person p = new Person();
                                p.setPersonName(personName);
                                p.setDate(date);

                                employees.add(p);
                        }

                }
                return employees;
        }
}
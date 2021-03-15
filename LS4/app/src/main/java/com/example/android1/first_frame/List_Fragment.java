package com.example.android1.first_frame;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_, container, false);
        sqlHelper = new DatabaseHelper(getActivity());
        db = sqlHelper.open();
           // получаем элемент по id из бд
              userCursor = db.rawQuery("select * from "+DatabaseHelper.TABLE_CONSTANS,new String[]{String.valueOf(1)});
           userCursor.moveToFirst();
           TextView tv = new TextView(getActivity());
            tv.setText(userCursor.getString(1));
           tv.setTextSize(30);
             view.addView(tv);
          // nameBox.setText(userCursor.getString(1));
           // yearBox.setText(String.valueOf(userCursor.getInt(2)));
            userCursor.close();
            sqlHelper.close();
      //  if (c.moveToFirst()){

       //     do {
       //         TextView tv = new TextView(getActivity());
       //         String KEY_NAME = c.getString(c.getColumnIndex("KEY_NAME"));
        //        tv.setText(KEY_NAME);
        //        tv.setTextSize(30);
        //        view.addView(tv);
        //    } while (c.moveToNext());


       // }

       // c.close();
       // dbHelper.close();



        return view;
    }
}
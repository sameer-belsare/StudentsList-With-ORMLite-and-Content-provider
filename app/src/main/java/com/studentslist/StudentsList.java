package com.studentslist;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class StudentsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        ContentValues values = new ContentValues();
        values.clear();
        values.put(StudentContract.FIRSTNAME, "Yamada");
        values.put(StudentContract.LASTNAME, "Tarou");
        values.put(StudentContract.AGE, "12");
        values.put(StudentContract.ADDRESS, "S sadlsa saldjls");
        getContentResolver().insert(StudentContract.CONTENT_URI, values);

        values.clear();
        values.put(StudentContract.FIRSTNAME, "Ackjsa");
        values.put(StudentContract.LASTNAME, "Cakldhsa");
        values.put(StudentContract.AGE, "11");
        values.put(StudentContract.ADDRESS, "Hlshd s; ;sjd");
        getContentResolver().insert(StudentContract.CONTENT_URI, values);

        Cursor cursor = getContentResolver().query(StudentContract.CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext())
        {
            for (int i = 0; i < cursor.getColumnCount(); i++)
            {
                Log.d(getClass().getSimpleName(), cursor.getColumnName(i) + " : " + cursor.getString(i));
            }
        }
        cursor.close();
    }
}

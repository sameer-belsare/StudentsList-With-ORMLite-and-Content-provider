package com.studentslist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

public class StudentsListActivity extends OrmLiteBaseActivity<StudentsHelper> implements View.OnClickListener {
    private RecyclerView listView;
    private ProgressBar progressBar;
    private TextView noStudentsText;
    private StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        //setTitle(getString(R.string.app_name));
        listView = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        noStudentsText = (TextView) findViewById(R.id.noStudentsText);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
            loadData();
            initAdapter();
    }

    private void loadData() {
            showProgress();
            /*ContentValues values = new ContentValues();
            values.clear();
            values.put(StudentContract.ROLLNUMBER, "100");
            values.put(StudentContract.FIRSTNAME, "Yamada");
            values.put(StudentContract.LASTNAME, "Tarou");
            values.put(StudentContract.AGE, "12");
            values.put(StudentContract.ADDRESS, "sadlsa saldjls");
            values.put(StudentContract.PHOTOURL, "");
            getContentResolver().insert(StudentContract.CONTENT_URI, values);

            values.clear();
            values.put(StudentContract.ROLLNUMBER, "101");
            values.put(StudentContract.FIRSTNAME, "Ackjsa");
            values.put(StudentContract.LASTNAME, "Cakldhsa");
            values.put(StudentContract.AGE, "11");
            values.put(StudentContract.ADDRESS, "Hlshd s; ;sjd");
            values.put(StudentContract.PHOTOURL, "");
            getContentResolver().insert(StudentContract.CONTENT_URI, values);*/
        Student student = new Student("sdasd", "dsa", 12, "dsadsa dsa", "");
        try {
            final Dao<Student, Integer> studentDao = getHelper().getDao();
            studentDao.createIfNotExists(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        noStudentsText.setVisibility(View.GONE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    public void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rlMain), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void initAdapter() {
        RuntimeExceptionDao<Student, Integer> simpleDao = getHelper().getSimpleDataDao();
        List<Student> list = simpleDao.queryForAll();

        noStudentsText.setVisibility(View.GONE);
        studentsAdapter = new StudentsAdapter(this, this);
        studentsAdapter.updateStudents();
        listView.setAdapter(studentsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);
        hideProgress();
        /*studentsCursor = getContentResolver().query(StudentContract.CONTENT_URI, null, null, null, null);
        students = new ArrayList<>();
        if(studentsCursor != null) {
            while (studentsCursor.moveToNext()) {
                Student student = new Student(studentsCursor.getInt(0), studentsCursor.getString(1), studentsCursor.getString(2),
                        studentsCursor.getInt(3), studentsCursor.getString(4), studentsCursor.getString(5));
                students.add(student);
                *//*for (int i = 0; i < studentsCursor.getColumnCount(); i++) {
                    //Log.d(getClass().getSimpleName(), studentsCursor.getColumnName(i) + " : " + studentsCursor.getString(i));
                    students.add(new Student(studentsCursor.getInt(i), studentsCursor.getString(i), studentsCursor.getString(i),
                            studentsCursor.getInt(i), studentsCursor.getString(i), studentsCursor.getString(i)));
                }*//*
            }
            studentsCursor.close();

            if (students != null && students.size() > 0) {
                noStudentsText.setVisibility(View.GONE);
                studentsAdapter = new StudentsAdapter(students, this, this);
                listView.setAdapter(studentsAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                listView.setLayoutManager(linearLayoutManager);
                listView.setHasFixedSize(true);
            }
        } else {
            showErrorMessage("No data found");
        }*/
    }

    @Override
    public void onClick(View view) {

    }
}

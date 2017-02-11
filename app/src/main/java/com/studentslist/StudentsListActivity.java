package com.studentslist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Created by sameer.belsare on 11/2/17.
 * Activity class to show list of students
 */
public class StudentsListActivity extends OrmLiteBaseActivity<StudentsHelper> implements View.OnClickListener {
    private RecyclerView listView;
    private ProgressBar progressBar;
    private TextView noStudentsText;
    private StudentsRecyclerViewCursorAdapter studentsAdapter;

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

    @Override
    protected void onResume() {
        super.onResume();
        Cursor studentsCursor = getContentResolver().query(StudentContract.CONTENT_URI, null, null, null, null);
        studentsAdapter.swapCursor(studentsCursor);
    }

    private void loadData() {
        showProgress();
        /**
         * Added dummy data using ORMLite APIs
         */
        RuntimeExceptionDao<Student, Integer> simpleDataDao = getHelper().getSimpleDataDao();
        for (int i=0; i<5; i++){
            Student student = new Student(i+1, (getResources().getStringArray(R.array.firstNameArray))[i], (getResources().getStringArray(R.array.lastNameArray))[i],
                    (getResources().getIntArray(R.array.ageArray))[i], "Address", "");
            simpleDataDao.createIfNotExists(student);
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
        noStudentsText.setVisibility(View.GONE);
        studentsAdapter = new StudentsRecyclerViewCursorAdapter(this, this);
        listView.setAdapter(studentsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);
        hideProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_main:

                break;
            case R.id.fab:
                Intent intent = new Intent(this, AddStudentActivity.class);
                startActivity(intent);
                break;
        }
    }
}

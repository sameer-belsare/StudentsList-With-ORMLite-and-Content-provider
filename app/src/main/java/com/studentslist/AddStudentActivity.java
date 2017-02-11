package com.studentslist;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by sameer.belsare on 11/2/17.
 * Activity class to add new student entry
 */
public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText address;
    private ImageView ivTakePhoto;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        age = (EditText) findViewById(R.id.age);
        address = (EditText) findViewById(R.id.address);
        ivTakePhoto = (ImageView) findViewById(R.id.ivTakePhoto);
        ivTakePhoto.setOnClickListener(this);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                addStudentToDB();
                break;
        }
    }

    private void addStudentToDB() {
        /**
         * Added using Content resolver
         */
        ContentValues values = new ContentValues();
        values.clear();
        values.put(StudentContract.FIRSTNAME, firstName.getText().toString().trim());
        values.put(StudentContract.LASTNAME, lastName.getText().toString().trim());
        values.put(StudentContract.AGE, Integer.valueOf(age.getText().toString().trim()));
        values.put(StudentContract.ADDRESS, address.getText().toString().trim());
        values.put(StudentContract.PHOTOURL, "");
        getContentResolver().insert(StudentContract.CONTENT_URI, values);
        Toast.makeText(this, getString(R.string.added_success), Toast.LENGTH_SHORT).show();
        finish();
    }
}

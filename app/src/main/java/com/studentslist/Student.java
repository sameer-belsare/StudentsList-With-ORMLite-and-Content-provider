package com.studentslist;

import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.Contract;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.DefaultContentMimeTypeVnd;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.DefaultContentUri;

/**
 * Created by sameer.belsare on 9/2/17.
 * Student data
 */
@Contract
@DatabaseTable(tableName = "students")
@DefaultContentUri(authority = "com.tojc.ormlite.android.ormlitecontentprovider", path = "students")
@DefaultContentMimeTypeVnd(name = "com.tojc.ormlite.android.ormlitecontentprovider", type = "students")
public class Student {
    @DatabaseField(columnName = BaseColumns._ID, id = true)
    private int rollNumber;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private int age;
    @DatabaseField
    private String address;
    @DatabaseField
    private String photoUrl;

    public Student(){

    }

    public Student(int rollNumber, String firstName, String lastName, int age, String address, String photoUrl) {
        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    public Student(String firstName, String lastName, int age, String address, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}

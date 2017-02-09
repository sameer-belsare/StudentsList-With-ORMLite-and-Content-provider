package com.studentslist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by sameer.belsare on 9/2/17.
 */

public class StudentsHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "studentsdata.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Student, Integer> simpleDao = null;
    private RuntimeExceptionDao<Student, Integer> simpleRuntimeDao = null;

    public StudentsHelper(Context context) {
        super(context, DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(StudentsHelper.class.getName(), "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, Student.class);
        } catch (SQLException e) {
            Log.e(StudentsHelper.class.getName(), "Can't create database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(StudentsHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Student.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(StudentsHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Student, Integer> getDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(Student.class);
        }
        return simpleDao;
    }

    public RuntimeExceptionDao<Student, Integer> getSimpleDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(Student.class);
        }
        return simpleRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        simpleDao = null;
        simpleRuntimeDao = null;
    }
}

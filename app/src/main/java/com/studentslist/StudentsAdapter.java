package com.studentslist;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sameer.belsare on 8/2/17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Student> students;
    private Context mContext;
    private View.OnClickListener mItemClickListener;

    public StudentsAdapter(Context context,
                           View.OnClickListener clickListener) {
        students = new ArrayList<>();
        mContext = context;
        mItemClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_item, parent,
                false);
        view.setOnClickListener(mItemClickListener);
        return new StudentsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentsListViewHolder viewHolder = (StudentsListViewHolder) holder;
        if(students != null && students.size() > 0) {
            Student student = students.get(position);
            String name = student.getFirstName();
            TextDrawable drawable;
            if (!TextUtils.isEmpty(name)) {
                drawable = TextDrawable.builder()
                        .buildRound(name.substring(0, 1), R.color.colorAccent);
            } else {
                drawable = TextDrawable.builder()
                        .buildRound("S", R.color.colorAccent);
            }
            viewHolder.profileImage.setImageDrawable(drawable);
            viewHolder.firstName.setText(student.getFirstName());
            viewHolder.lastName.setText(student.getLastName());
            viewHolder.itemView.setTag(student.getRollNumber());
            viewHolder.age.setText("Age: " + student.getAge());
        }
    }

    @Override
    public int getItemCount() {
        return (students != null ? students.size() : 0);
    }

    private static class StudentsListViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImage;
        public TextView firstName;
        public TextView lastName;
        public TextView age;

        public StudentsListViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            firstName = (TextView) itemView.findViewById(R.id.firstName);
            lastName = (TextView) itemView.findViewById(R.id.lastName);
            age = (TextView) itemView.findViewById(R.id.age);
        }
    }

    public void updateStudents() {
        Cursor studentsCursor = mContext.getContentResolver().query(StudentContract.CONTENT_URI, null, null, null, null);
        if(studentsCursor != null) {
            while (studentsCursor.moveToNext()) {
                Student student = new Student(studentsCursor.getInt(0), studentsCursor.getString(1), studentsCursor.getString(2),
                        studentsCursor.getInt(3), studentsCursor.getString(4), studentsCursor.getString(5));
                students.add(student);
            }
            studentsCursor.close();
        }
    }

}

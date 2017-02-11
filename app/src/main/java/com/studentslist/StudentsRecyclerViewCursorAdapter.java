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


/**
 * Created by sameerbelsare on 11/02/17.
 * RecycleView Cursor adapter for student list
 */

public class StudentsRecyclerViewCursorAdapter extends RecyclerViewCursorAdapter<StudentsRecyclerViewCursorAdapter.StudentsListViewHolder>{
    private final Context mContext;
    private final View.OnClickListener mItemClickListener;

    public StudentsRecyclerViewCursorAdapter(Context context, View.OnClickListener clickListener) {
        super(null);
        mContext = context;
        mItemClickListener = clickListener;
    }

    @Override
    public StudentsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_item, parent,
                false);
        view.setOnClickListener(mItemClickListener);
        return new StudentsListViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(StudentsListViewHolder holder, Cursor studentsCursor) {
        if(studentsCursor != null) {
                String name = studentsCursor.getString(studentsCursor.getColumnIndexOrThrow("firstName"));
                TextDrawable drawable;
                if (!TextUtils.isEmpty(name)) {
                    drawable = TextDrawable.builder()
                            .buildRound(name.substring(0, 1), R.color.colorAccent);
                } else {
                    drawable = TextDrawable.builder()
                            .buildRound("S", R.color.colorAccent);
                }
                holder.profileImage.setImageDrawable(drawable);
                holder.firstName.setText(studentsCursor.getString(studentsCursor.getColumnIndexOrThrow("firstName")));
                holder.lastName.setText(studentsCursor.getString(studentsCursor.getColumnIndexOrThrow("lastName")));
                holder.itemView.setTag(studentsCursor.getInt(studentsCursor.getColumnIndexOrThrow("_id")));
                holder.age.setText("Age: " + studentsCursor.getInt(studentsCursor.getColumnIndexOrThrow("age")));
        }
    }

    public static class StudentsListViewHolder extends RecyclerView.ViewHolder {
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
}

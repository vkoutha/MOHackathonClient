package org.mort11.mohackathonclient.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.mort11.mohackathonclient.R;
import org.mort11.mohackathonclient.student.Student;

import java.util.ArrayList;

public class StudentListViewAdapter extends ArrayAdapter<Student> {

    private static class ViewHolder{
        TextView studentNameTextView;
        TextView studentUsernameTextView;
        TextView availableReportsTextView;
    }

    public StudentListViewAdapter(Context context, ArrayList<Student> students){
        super(context, R.layout.student_list_view_layout, students);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student student = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.student_list_view_layout, parent, false);
            viewHolder.studentNameTextView = convertView.findViewById(R.id.listViewStudentName);
            viewHolder.studentUsernameTextView = convertView.findViewById(R.id.listViewStudentUsername);
            viewHolder.availableReportsTextView = convertView.findViewById(R.id.listViewReportsCount);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.studentNameTextView.setText("Student Name: " + student.getFullName());
        viewHolder.studentUsernameTextView.setText("Username: " + student.getUsername());
        viewHolder.availableReportsTextView.setText(student.getDailyReports().size() + " Available Reports");
        return convertView;
    }
}

package org.mort11.mohackathonclient.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mort11.mohackathonclient.Client;
import org.mort11.mohackathonclient.R;
import org.mort11.mohackathonclient.student.Student;

import java.io.IOException;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Log.d("test", "Got to the Admin Activity");
        ArrayList<String> studentJSONs = Client.getStudentsFromServer();
        Log.d("test", "Received student jsons");
        ArrayList<Student> students = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String accountJSON = (String) getIntent().getExtras().get("json");
        Admin admin = gson.fromJson(accountJSON, Admin.class);
        for(String s : studentJSONs){
            Student student = gson.fromJson(s, Student.class);
            students.add(student);
            Log.d("test", s);
        }
        Client.studentJSONs = new ArrayList<>();
        final ArrayList<Student> studentsers = students;
        TextView welcomeTextView = findViewById(R.id.welcomeAdminTextView);
        welcomeTextView.setText("Welcome " + admin.getFullName());
        TextView availableStudents = findViewById(R.id.adminRegisteredStudentsTextView);
        availableStudents.setText("You have " + students.size() + " registered students");
        ListView studentsListView = findViewById(R.id.studentsListView);
        StudentListViewAdapter studentListViewAdapter = new StudentListViewAdapter(getApplicationContext(), students);
        studentsListView.setAdapter(studentListViewAdapter);
        final ReportsDialogFragment reportsDialogFragment = new ReportsDialogFragment();
        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student selectedStudent = (Student) adapterView.getItemAtPosition(i);
                Log.d("test", "Student Reports: " + selectedStudent.getDailyReports());
                testerDialogActivity.reports = selectedStudent.getDailyReports();
                //ReportsDialogFragment.reports = selectedStudent.getDailyReports();
                //reportsDialogFragment.show(getSupportFragmentManager(), "Reports");
                Intent intent = new Intent(getApplicationContext(), testerDialogActivity.class);
                startActivity(intent);
            }
        });
    }

}

package org.mort11.mohackathonclient.student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mort11.mohackathonclient.Client;
import org.mort11.mohackathonclient.MainActivity;
import org.mort11.mohackathonclient.R;

import java.io.IOException;
import java.util.Calendar;

public class StudentActivity extends AppCompatActivity {

    private Student student;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        String json = (String) getIntent().getExtras().get("json");
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        student = gson.fromJson(json, Student.class);
        TextView welcomeText = findViewById(R.id.studentWelcomeTextView);
        welcomeText.setText("Welcome " + student.getFullName() + "!");
        TextView teacherText = findViewById(R.id.studentsTeacherTextView);
        teacherText.setText("Teacher: " + student.getTeacherName());
        final TextView date = findViewById(R.id.datePicker);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        StudentActivity.this,
                        R.style.ThemeOverlay_AppCompat_Dark,
                        dateSetListener,
                        year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText((month + 1) + "/" + day + "/" + year);
            }
        };
        Button submitButton = findViewById(R.id.submitReportButton);
        final RadioButton beenSick = findViewById(R.id.yesSickness);
        final RadioButton contactWithSick = findViewById(R.id.yesContact);
        final RadioButton beenWithDoctor = findViewById(R.id.yesDoctor);
        final RadioButton needAssistance = findViewById(R.id.yesAssistance);
        final EditText additionalInfo = findViewById(R.id.elaborateEditText);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DailyReport report = new DailyReport("6/6/2020", beenSick.isChecked(), contactWithSick.isChecked(),
                        beenWithDoctor.isChecked(), needAssistance.isChecked(), additionalInfo.getEditableText().toString());
                Log.d("test", "" + beenSick.isChecked());
                Log.d("test", additionalInfo.getEditableText().toString());
                student.addReport(report);
                String newJSON = gson.toJson(student);
                Log.d("test", "New student json is: " + newJSON);
                Log.d("test", "Sending report to server!!!");
                Client.sendReport(newJSON);
                while (!Client.reportSentToServer)
                    ;
                Client.reportSentToServer = false;
                Log.d("test", "Report sent to server!!");
                Toast.makeText(getApplicationContext(), "Report sent successfully!", Toast.LENGTH_SHORT);
                Intent intent = new Intent(StudentActivity.this, MainActivity.class);
                Client.closeConnection();
                Client.connectToServer();
                startActivity(intent);
            }
        });
    }
}

package org.mort11.mohackathonclient.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.mort11.mohackathonclient.R;

public class ViewReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_report_admin_layout);
        String date = getIntent().getStringExtra("date");
        boolean sickness = getIntent().getBooleanExtra("sickness", false);
        boolean contact = getIntent().getBooleanExtra("contact", false);
        boolean doctor = getIntent().getBooleanExtra("doctor", false);
        boolean assistance = getIntent().getBooleanExtra("assistance", false);
        String additionalInfo = getIntent().getStringExtra("additional_info");
        TextView dateTextView = findViewById(R.id.adminDate);
        RadioButton sicknessY = findViewById(R.id.adminYesSickness);
        RadioButton contactY = findViewById(R.id.adminYesContact);
        RadioButton doctorY = findViewById(R.id.adminYesDoctor);
        RadioButton assistanceY = findViewById(R.id.adminYesAssistance);
        RadioButton sicknessN = findViewById(R.id.adminNoSickness);
        RadioButton contactN = findViewById(R.id.adminNoContact);
        RadioButton doctorN = findViewById(R.id.adminNoDoctor);
        RadioButton assistanceN = findViewById(R.id.adminNoAssistance);
        TextView additionalInfoTextView = findViewById(R.id.adminElaborateTextView);
        dateTextView.setText(date);
        if (sickness){
           sicknessY.setChecked(true);
        }else{
            sicknessN.setChecked(true);
        }
        if (contact){
            contactY.setChecked(true);
        }else{
            contactN.setChecked(true);
        }
        if (doctor){
            doctorY.setChecked(true);
        }else{
            doctorN.setChecked(true);
        }
        if (assistance){
            assistanceY.setChecked(true);
        }else{
            assistanceN.setChecked(true);
        }
        additionalInfoTextView.setText(additionalInfo);
    }
}

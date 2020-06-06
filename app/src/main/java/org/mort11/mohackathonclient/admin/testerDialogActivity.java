package org.mort11.mohackathonclient.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.mort11.mohackathonclient.R;
import org.mort11.mohackathonclient.student.DailyReport;

import java.util.ArrayList;

public class testerDialogActivity extends AppCompatActivity {

    public static ArrayList<DailyReport> reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_dialog_fragment);
        ListView reportsListView = findViewById(R.id.reports_list_view);
        ReportsListViewAdapter reportsListViewAdapter = new ReportsListViewAdapter(getApplicationContext(), reports);
        reportsListView.setAdapter(reportsListViewAdapter);
        reportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DailyReport report = (DailyReport) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), ViewReportActivity.class);
                intent.putExtra("date", report.getDate());
                intent.putExtra("sickness", report.experiencedSickness());
                intent.putExtra("contact", report.hasContact());
                intent.putExtra("doctor", report.needDoctor());
                intent.putExtra("assistance", report.needAssistance());
                intent.putExtra("additional_info", report.getAdditionalInfo());
                startActivity(intent);
            }
        });
    }

}

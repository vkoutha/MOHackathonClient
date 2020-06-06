package org.mort11.mohackathonclient.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.mort11.mohackathonclient.R;
import org.mort11.mohackathonclient.student.DailyReport;

import java.util.ArrayList;

public class ReportsDialogFragment extends DialogFragment {

    public static ArrayList<DailyReport> reports;
    public ReportsDialogFragment(){
        reports = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.report_dialog_fragment, container, false);
        ListView reportsListView = view.findViewById(R.id.reports_list_view);
        ReportsListViewAdapter reportsListViewAdapter = new ReportsListViewAdapter(getContext(), reports);
        reportsListView.setAdapter(reportsListViewAdapter);
        reportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DailyReport report = (DailyReport) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), ViewReportActivity.class);
                intent.putExtra("date", report.getDate());
                intent.putExtra("sickness", report.experiencedSickness());
                intent.putExtra("contact", report.hasContact());
                intent.putExtra("doctor", report.needDoctor());
                intent.putExtra("assistance", report.needAssistance());
                intent.putExtra("additional_info", report.getAdditionalInfo());
                startActivity(intent);
            }
        });
        return view;
    }
}

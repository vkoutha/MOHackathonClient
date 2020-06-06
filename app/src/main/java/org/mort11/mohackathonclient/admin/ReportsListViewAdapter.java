package org.mort11.mohackathonclient.admin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.mort11.mohackathonclient.R;
import org.mort11.mohackathonclient.student.DailyReport;

import java.util.ArrayList;

public class ReportsListViewAdapter extends ArrayAdapter<DailyReport> {

    private static class ViewHolder{
        TextView dateTextView;
    }

    public ReportsListViewAdapter(Context context, ArrayList<DailyReport> reports){
        super(context, R.layout.report_list_view_layout, reports);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DailyReport report = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.report_list_view_layout, parent, false);
            viewHolder.dateTextView = convertView.findViewById(R.id.reportListViewDate);
        }else{
            viewHolder = (ReportsListViewAdapter.ViewHolder) convertView.getTag();
        }
        Log.d("test", "DATE: " + report.getDate());
        viewHolder.dateTextView.setText(report.getDate());
        return convertView;
    }
}

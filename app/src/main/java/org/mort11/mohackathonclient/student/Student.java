package org.mort11.mohackathonclient.student;

import java.util.ArrayList;

public class Student {

    private String fullName;
    private String username;
    private String password;
    private String teacherName;
    private ArrayList<DailyReport> dailyReports;

    public Student(String fullName, String username, String password, String teacherName) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.teacherName = teacherName;
        dailyReports = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addReport(DailyReport report) {
        dailyReports.add(report);
    }

    public ArrayList<DailyReport> getDailyReports() {
        return dailyReports;
    }

}


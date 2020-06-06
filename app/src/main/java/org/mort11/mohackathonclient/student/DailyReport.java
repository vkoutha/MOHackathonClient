package org.mort11.mohackathonclient.student;

import java.io.Serializable;

public class DailyReport implements Serializable {

    private String date;
    private boolean experiencedSickness;
    private boolean hasContact;
    private boolean needDoctor;
    private boolean needAssistance;
    private String additionalInfo;

    public DailyReport(String date, boolean experiencedSickness, boolean hasContact, boolean needDoctor, boolean needAssistance, String additionalInfo){
        this.date = date;
        this.experiencedSickness = experiencedSickness;
        this.hasContact = hasContact;
        this.needDoctor = needDoctor;
        this.needAssistance = needAssistance;
        this.additionalInfo = additionalInfo;
    }

    public String getDate(){
        return date;
    }

    public boolean experiencedSickness() {
        return experiencedSickness;
    }

    public boolean hasContact() {
        return hasContact;
    }

    public boolean needDoctor() {
        return needDoctor;
    }

    public boolean needAssistance() {
        return needAssistance;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

}

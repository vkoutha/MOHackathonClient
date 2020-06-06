package org.mort11.mohackathonclient.student;

public class DailyReport {

    private boolean experiencedSickness;
    private boolean hasContact;
    private boolean needDoctor;
    private boolean needAssistance;
    private String additionalInfo;

    public DailyReport(boolean experiencedSickness, boolean hasContact, boolean needDoctor, boolean needAssistance, String additionalInfo){
        this.experiencedSickness = experiencedSickness;
        this.hasContact = hasContact;
        this.needDoctor = needDoctor;
        this.needAssistance = needAssistance;
        this.additionalInfo = additionalInfo;
    }



    public void submit(){

    }

}

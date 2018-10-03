package com.eveati.sajid.teslar;

class Freelancer {



    private String complaintId ;
    private String title ;
    private String description ;
    private String status ;
    private String submissiveDate ;
    private String lastVisitDate ;
    private String remark ;
    private String closingDate ;


    public Freelancer (String complaintId1 , String title1 , String description1 , String status1 , String submissiveDate1 , String lastVisitDate1 , String remark1 , String closingDate1){

        this.complaintId = complaintId1 ;
        this.title = title1 ;
        this.description = description1 ;
        this.status = status1 ;
        this.submissiveDate = submissiveDate1 ;
        this.lastVisitDate = lastVisitDate1 ;
        this.remark = remark1 ;
        this.closingDate = closingDate1 ;



    }

    public String getComplaintId() {
        return complaintId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getSubmissiveDate() {
        return submissiveDate;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public String getRemark() {
        return remark;
    }

    public String getClosingDate() {
        return closingDate;
    }
}


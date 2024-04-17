package com.talent_insider.frontend;

public class Gig {
    private String jobTitle;
    private String description;
    private String skills;
    private String timeExpected; 
    private String paymentAmount;
    
    public Gig(String jobTitle, String description, String companyName, String timeExpected, String paymentAmount) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.skills = companyName;
        this.timeExpected = timeExpected;
        this.paymentAmount = paymentAmount;
    }
    public String getJobTitle() {
        return "Title: " + jobTitle;
    }
    public String getDescription() {
        return "Description: " + description;
    }
    public String getSkills() {
        return "Skills: " + skills;
    }
    public String getTimeExpected() {
        return "Deadline: " + timeExpected;
    }
    public String getPaymentAmount() {
        return "Perks: " + paymentAmount;
    }
    
} 

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
        return jobTitle;
    }
    public String getDescription() {
        return description;
    }
    public String getSkills() {
        return skills;
    }
    public String getTimeExpected() {
        return timeExpected;
    }
    public String getPaymentAmount() {
        return paymentAmount;
    }
    
} 

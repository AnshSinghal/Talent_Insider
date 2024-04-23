package com.talent_insider.frontend;

public class Applicant {
    private String name;
    private String number;
    private String email;
    private String age;
    private String skills;
    private String bio;
    private String experience;
    public Applicant(String name, String number, String email, String age, String skills, String bio,
            String experience) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.age = age;
        this.skills = skills;
        this.bio = bio;
        this.experience = experience;
    }
    public String getName() {
        return "Name: " + name;
    }
    public String getNumber() {
        return "Number: " + number;
    }
    public String getEmail() {
        return "Email: " + email;
    }
    public String getAge() {
        return "Age: " + age;
    }
    public String getSkills() {
        return "Skills: " + skills;
    }
    public String getBio() {
        return "Bio: " + bio;
    }
    public String getExperience() {
        return "Experience: " + experience;
    }


}

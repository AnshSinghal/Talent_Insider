package com.talent_insider.frontend;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicantPanel extends JPanel{
    private Applicant applicant;
    public ApplicantPanel(Applicant applicant) {
        this.applicant = applicant;
        setLayout(new GridLayout(7, 1));
        add(new JLabel(applicant.getName()));
        add(new JLabel(applicant.getNumber()));
        add(new JLabel(applicant.getEmail()));
        add(new JLabel(applicant.getAge()));
        add(new JLabel(applicant.getSkills()));
        add(new JLabel(applicant.getBio()));
        add(new JLabel(applicant.getExperience()));
    }
}

package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CreateProfileTalent extends JFrame{
    CreateProfileTalent(){
        this.setVisible(true);
        this.setTitle("Profile");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Name
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        nameField.setColumns(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        // Age
        JPanel agePanel = new JPanel();
        JLabel ageLabel = new JLabel("Age: ");
        JTextField ageField = new JTextField();
        ageField.setColumns(20);
        agePanel.add(ageLabel);
        agePanel.add(ageField);
        // Bio
        JPanel bioPanel = new JPanel();
        JLabel bioLabel = new JLabel("Bio: ");
        JTextField bioField = new JTextField();
        bioField.setColumns(20);
        bioPanel.add(bioLabel);
        bioPanel.add(bioField);
        // Skills
        JPanel skillsPanel = new JPanel();
        JLabel skillsLabel = new JLabel("Skills: ");
        JTextField skillsField = new JTextField();
        skillsField.setColumns(20);
        skillsPanel.add(skillsLabel);
        skillsPanel.add(skillsField);
        // Experience
        JPanel experiencePanel = new JPanel();
        JLabel experienceLabel = new JLabel("Experience: ");
        JTextField descriptionField = new JTextField();
        descriptionField.setColumns(20);
        experiencePanel.add(experienceLabel);
        experiencePanel.add(descriptionField);
        // Login Button
        JPanel loginButtonPanel = new JPanel();
        final JButton loginButton = new JButton("Submit");
        loginButtonPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(loginButton);
                if (window != null) {
                    window.dispose();
                }
                new TalentProfileWindow((JFrame) window);
            }
        });
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(namePanel);
        mainPanel.add(agePanel);
        mainPanel.add(bioPanel);
        mainPanel.add(skillsPanel);
        mainPanel.add(experiencePanel);
        mainPanel.add(loginButtonPanel);

        add(mainPanel, BorderLayout.CENTER);
    }
}

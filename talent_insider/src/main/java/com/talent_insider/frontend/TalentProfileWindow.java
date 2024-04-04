package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TalentProfileWindow extends JFrame {
    final JFrame previousWindow;

    TalentProfileWindow(JFrame previousWindow) {
        this.previousWindow = previousWindow;
        this.setVisible(true);
        this.setTitle("Profile");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 1));

        // Name
        JLabel nameLabel = new JLabel("Name: " + getTalentName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(nameLabel);

        // age
        JLabel ageLabel = new JLabel("age: " + getTalentAge());
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(ageLabel);

        // Bio
        JLabel bioLabel = new JLabel("Bio: " + getTalentBio());
        bioLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(bioLabel);

        // Skills
        JLabel skillsLabel = new JLabel("Skills: " + getTalentSkills());
        skillsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(skillsLabel);

        // Experience
        JLabel experienceLabel = new JLabel("Experience: " + getTalentExperience());
        experienceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(experienceLabel);

        // Logout Button Panel
        JPanel logoutButtonPanel = new JPanel();
        final JButton logoutButton = new JButton("Logout");
        final JFrame prevWindow = this.previousWindow;
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // 1. Prepare the URL
                    String endpoint = "https://api.example.com/data";
                    URL url = new URL(endpoint);

                    // 2. Open the connection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    // 3. Get the response code
                    int responseCode = connection.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    // 4. Process the response
                    if (responseCode == 200) { // Success
                        try (BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()))) {
                            String inputLine;
                            StringBuilder response = new StringBuilder();
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            // Process the response (Example: Display in a JTextArea)
                            JTextArea textArea = new JTextArea(response.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            JOptionPane.showMessageDialog(null, scrollPane);
                        }
                    } else {
                        // Handle error
                        System.out.println("Request failed. Response Code: " + responseCode);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Window window = SwingUtilities.getWindowAncestor(logoutButton);
                if (window != null) {
                    window.dispose();
                }
                if (prevWindow != null) {
                    prevWindow.dispose();
                }
                Main.main(null);
            }
        });
        logoutButtonPanel.add(logoutButton);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(logoutButtonPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private String getTalentExperience() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getTalentSkills() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getTalentBio() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getTalentAge() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getTalentName() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }
}

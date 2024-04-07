package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        JTextField experienceField = new JTextField();
        experienceField.setColumns(20);
        experiencePanel.add(experienceLabel);
        experiencePanel.add(experienceField);
        // Login Button
        JPanel loginButtonPanel = new JPanel();
        final JButton loginButton = new JButton("Submit");
        loginButtonPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // 1. Prepare the URL
                    String endpoint = "http://localhost:8080/ansh_singhal/userProfile?name="+ nameField.getText() +"&age="+ ageField.getText() +"&bio="+ bioField.getText() +"&skills="+ skillsField.getText() +"&experience=" + experienceField.getText();
                    URL url = new URL(endpoint);

                    // 2. Open the connection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true); // Enable sending a request body

                    // 3. Set headers (if needed)
                    connection.setRequestProperty("Content-Type", "application/json");

                    // 4. Prepare the request body (if needed)
                    String postData = "{ \"key1\": \"value1\", \"key2\": \"value2\" }"; // Example JSON
                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(postData.getBytes());
                    }

                    // 5. Get the response code
                    int responseCode = connection.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    // 6. Process the response (if needed)
                    if (responseCode == 200 || responseCode == 201) { // Success codes
                        // Read response using InputStream from connection
                    } else {
                        // Handle error
                    }

                } catch (Exception ex) {
                    // Handle exceptions (e.g., network errors)
                    ex.printStackTrace();
                }

                Window window = SwingUtilities.getWindowAncestor(loginButton);
                if (window != null) {
                    window.dispose();
                }
                new TalentProfileWindow((JFrame) window, nameField.getText(), Integer.parseInt(ageField.getText()), bioField.getText(), skillsField.getText(), experienceField.getText());
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

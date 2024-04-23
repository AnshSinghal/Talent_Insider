package com.talent_insider.frontend;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.net.URLEncoder;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
// import java.StringBuilder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;


public class GigWindow extends JFrame {
    boolean isTalent;
    String username;
    String name;
    boolean isClient;
    JSONArray jsonArray;

    String tableName;
    String Name;
    String number;
    String email;
    String age;
    String bio;
    String skills;
    String experience;

    GigWindow(Gig gig, boolean isTalent, String username, String name, boolean isClient, JSONArray jsonArray) {
        this.isClient = isClient;
        this.jsonArray = jsonArray;
        this.name = name;
        this.username = username;
        this.isTalent = isTalent;
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(gig.getJobTitle());

        // Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(7, 1));

        // Job Title
        JLabel jobTitleLabel = new JLabel(gig.getJobTitle());
        jobTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        detailsPanel.add(jobTitleLabel);

        // Description
        JLabel descriptionLabel = new JLabel(gig.getDescription());
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsPanel.add(descriptionLabel);

        // Skills
        JLabel skillsLabel = new JLabel(gig.getSkills());
        skillsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsPanel.add(skillsLabel);

        // Time Expected
        JLabel timeExpectedLabel = new JLabel(gig.getTimeExpected());
        timeExpectedLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsPanel.add(timeExpectedLabel);

        // Payment Amount
        JLabel paymentAmountLabel = new JLabel(gig.getPaymentAmount());
        paymentAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        detailsPanel.add(paymentAmountLabel);

        // Comments Label
        JLabel commentsLabel = new JLabel("Add some comments:");
        commentsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsPanel.add(commentsLabel);

        // Comments Text Area
        JTextArea commentsTextArea = new JTextArea();
        if (isTalent)detailsPanel.add(commentsTextArea);

        // Apply Button Panel
        JPanel applyButtonPanel = new JPanel();
        applyButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Apply Button
        final JButton applyButton = new JButton("Apply");
        if (isTalent) applyButtonPanel.add(applyButton);
        JPanel applicantsPanel = new JPanel();
        // applicantsPanel.setLayout(new GridLayout(7, 1));
        JLabel applicantsLabel = new JLabel("Applicants: ");
        applicantsPanel.add(applicantsLabel);
        for (int i = 0; i < jsonArray.length(); i++) {
            JLabel nameLabel = new JLabel("Name: ");
            JLabel numberLabel = new JLabel("Number: ");
            JLabel emailLabel = new JLabel("Email: ");
            JLabel ageLabel = new JLabel("Age: ");
            JLabel bioLabel = new JLabel("Bio: ");
            JLabel skillsLabel2 = new JLabel("Skills: ");
            JLabel experienceLabel = new JLabel("Experience: ");
            nameLabel.setText(nameLabel.getText() + jsonArray.getJSONObject(i).getString("name") + ", ");
            numberLabel.setText(numberLabel.getText() + jsonArray.getJSONObject(i).getString("number") + ", ");
            emailLabel.setText(emailLabel.getText() + jsonArray.getJSONObject(i).getString("email") + ", ");
            ageLabel.setText(ageLabel.getText() + jsonArray.getJSONObject(i).getString("age") + ", ");
            bioLabel.setText(bioLabel.getText() + jsonArray.getJSONObject(i).getString("bio") + ", ");
            skillsLabel2.setText(skillsLabel.getText() + jsonArray.getJSONObject(i).getString("skills") + ", ");
            experienceLabel.setText(experienceLabel.getText() + jsonArray.getJSONObject(i).getString("experience") + ", ");

            applicantsLabel.setFont(new Font("Arial", Font.BOLD, 18));
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            numberLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            ageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            bioLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            skillsLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
            experienceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            
            applicantsPanel.add(nameLabel);
            applicantsPanel.add(numberLabel);
            applicantsPanel.add(emailLabel);
            applicantsPanel.add(ageLabel);
            applicantsPanel.add(bioLabel);
            applicantsPanel.add(skillsLabel2);
            applicantsPanel.add(experienceLabel);
            if (isClient) detailsPanel.add(applicantsPanel, BorderLayout.SOUTH);
        }

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // String urlName = URLEncoder.encode(name, "UTF-8");
                    // String urlUsername = URLEncoder.encode(username, "UTF-8");
                    String url1Endpoint = "http://localhost:8080/ansh_singhal/userProfile?name="+ name +"&age=&bio=&skills=&experience=";
                    String url2Enpoint = "http://localhost:8080/ansh_singhal/login?name="+ "&username="+username+"&password=&email=&number=";
                    
                    System.out.println("URL1: " + url1Endpoint);
                    System.out.println("URL2: " + url2Enpoint);

                    URL url1 = new URL(url1Endpoint);
                    URL url2 = new URL(url2Enpoint);
                    HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                    connection1.setRequestMethod("GET");
                    // connection1.setDoOutput(true); // Enable sending a request body
                    HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                    connection2.setRequestMethod("GET");
                    // connection2.setDoOutput(true);
                    // connection1.setRequestProperty("Content-Type", "application/json");
                    // connection2.setRequestProperty("Content-Type", "application/json");
                    // 4. Prepare the request body (if needed)
                    // String postData = "{ \"key1\": \"value1\", \"key2\": \"value2\" }"; // Example JSON
                    // try (OutputStream os = connection1.getOutputStream()) {
                    //     os.write(postData.getBytes());
                    // }
                    // try (OutputStream os = connection2.getOutputStream()) {
                    //     os.write(postData.getBytes());
                    // }

                    // 5. Get the response code
                    int responseCode1 = connection1.getResponseCode();
                    int responseCode2 = connection2.getResponseCode();
                    System.out.println("Got Profile DAta code" + responseCode1);
                    System.out.println("Got login data code " + responseCode2);
                    // 6. Process the response (if needed)
                    if (responseCode1 == 200 || responseCode1 == 200) { // Success codes
                        // Read response using InputStream from connection
                        try (BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection1.getInputStream()))) {
                            String inputLine;
                            StringBuilder response1 = new StringBuilder();
                            while ((inputLine = in.readLine()) != null) {
                                response1.append(inputLine);
                                JSONObject jsonResponse1 = new JSONObject(response1.toString());
                                System.out.println(jsonResponse1.toString());
                                Name = jsonResponse1.getString("name");
                                // email = jsonResponse1.getString("email");
                                age = Integer.toString(jsonResponse1.getInt("age"));
                                bio = jsonResponse1.getString("bio");
                                skills = jsonResponse1.getString("skills");
                                experience = jsonResponse1.getString("experience");
                            }}
                            try (BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection2.getInputStream()))) {
                            String inputLine;
                            StringBuilder response2 = new StringBuilder();
                            while ((inputLine = in.readLine()) != null) {
                                response2.append(inputLine);
                                JSONObject jsonResponse2 = new JSONObject(response2.toString());
                                System.out.println(jsonResponse2.toString());
                                number = jsonResponse2.getString("number");
                                email = jsonResponse2.getString("email");
                            }}
                            System.out.println("Name: " + Name);
                            System.out.println("Number: " + number);
                            System.out.println("Email: " + email);
                            System.out.println("Age: " + age);
                            System.out.println("Bio: " + bio);
                            System.out.println("Skills: " + skills);
                            System.out.println("Experience: " + experience);

                    } else {
                        // Handle error
                        try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection1.getInputStream()))) {
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        JSONArray jsonResponse = new JSONArray(response.toString());
                        System.out.println(jsonResponse.toString());
        
                    
                    }

                }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                try {
                    // 1. Prepare the URL
                    String nameUrl = URLEncoder.encode(Name, "UTF-8");
                    String numberUrl = URLEncoder.encode(number, "UTF-8");
                    String emailUrl = URLEncoder.encode(email, "UTF-8");
                    String ageUrl = URLEncoder.encode(age, "UTF-8");
                    String bioUrl = URLEncoder.encode(bio, "UTF-8");
                    String skillsUrl = URLEncoder.encode(skills, "UTF-8");
                    String experienceUrl = URLEncoder.encode(experience, "UTF-8");
            // Construct the encoded URL
            String urlString1 = String.format("http://localhost:8080/ansh_singhal/jobDatas?tableName=%s&name=%s&number=%s&email=%s&age=%s&bio=%s&skills=%s&experience=%s",
                                              gig.getTitle(), nameUrl, numberUrl, emailUrl, ageUrl, bioUrl, skillsUrl, experienceUrl);
                    // String urlString2 = String.format("http://localhost:8080/ansh_singhal/jobApp?tableName=%s",title);
                    // String endpoint = "http://localhost:8080/ansh_singhal/job?title="+textField1.getText()+"&description="+textField2.getText()+"&skills="+textField3.getText()+"&salary="+textField5.getText()+"&deadline="+textField4.getText();
                    // String endpoint = "http://localhost:8080/ansh_singhal/job?title=Hackathon Team Member&description=We need a team member for our team in Smart India Hackathon 2024.&skills=React JS, Django&salary=There is a prize pool of 1 Lac.&deadline=1 week";
                    URL url1 = new URL(urlString1);
                    // 2. Open the connection
                    HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                    connection1.setRequestMethod("POST");
                    connection1.setDoOutput(true); // Enable sending a request body
                   
                    // 3. Set headers (if needed)
                    connection1.setRequestProperty("Content-Type", "application/json");
                    // connection2.setRequestProperty("Content-Type", "application/json");
                    // 4. Prepare the request body (if needed)
                    String postData = "{ \"key1\": \"value1\", \"key2\": \"value2\" }"; // Example JSON
                    try (OutputStream os = connection1.getOutputStream()) {
                        os.write(postData.getBytes());
                    }
                    // try (OutputStream os = connection2.getOutputStream()) {
                    //     os.write(postData.getBytes());
                    // }

                    // 5. Get the response code
                    int responseCode1 = connection1.getResponseCode();
                    // int responseCode2 = connection2.getResponseCode();
                    System.out.println("Data Posted: " + responseCode1);
                    // System.out.println("Table Created Response Code: " + responseCode2);
                    // 6. Process the response (if needed)
                    if (responseCode1 == 200 || responseCode1 == 201) { // Success codes
                        // Read response using InputStream from connection

                    } else {
                        // Handle error
                        try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection1.getInputStream()))) {
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        JSONArray jsonResponse = new JSONArray(response.toString());
                        System.out.println(jsonResponse.toString());
        
                        // try (BufferedReader in2 = new BufferedReader(
                        //     new InputStreamReader(connection2.getInputStream()))) {
                        // String inputLine2;
                        // StringBuilder response2 = new StringBuilder();
                        // while ((inputLine2 = in2.readLine()) != null) {
                        //     response2.append(inputLine2);
                        // }
                        // JSONArray jsonResponse2 = new JSONArray(response2.toString());
                        // System.out.println(jsonResponse2.toString());
                    }

                }
            // }
        } catch (Exception ex) {
                    // Handle exceptions (e.g., network errors)
                    ex.printStackTrace();
                }

                Window window = SwingUtilities.getWindowAncestor(applyButton);
                if (window != null) {
                    window.dispose();
                }
                // Main.main(null);
            }
        });

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(applyButtonPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}

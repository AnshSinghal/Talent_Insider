package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BorderFactory;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;

public class NewReq extends JFrame {
    JFrame prevWindow;
    String username;
    public NewReq(JFrame prevWindow, String username) {
        this.username = username;
        this.prevWindow = prevWindow;
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 5, 5)); // 5 rows, 2 columns, spacing

        JLabel titleLabel = new JLabel("Post a Requirement");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // text fields and labels
        JLabel label1 = new JLabel("Job Title:");
        label1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        contentPanel.add(label1);
        JTextField textField1 = new JTextField();
        // textField1.setPreferredSize(new Dimension(200, 2));
        textField1.setColumns(20);
        contentPanel.add(textField1);

        JLabel label2 = new JLabel("Description:");
        label2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        contentPanel.add(label2);
        JTextField textField2 = new JTextField();
        // textField2.setPreferredSize(new Dimension(200, 2));
        textField2.setColumns(20);
        contentPanel.add(textField2);

        JLabel label3 = new JLabel("Skills Required:");
        label3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        contentPanel.add(label3);
        JTextField textField3 = new JTextField();
        // textField3.setPreferredSize(new Dimension(200, 2));
        textField3.setColumns(20);
        contentPanel.add(textField3);

        JLabel label4 = new JLabel("Time Expected:");
        label4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        contentPanel.add(label4);
        JTextField textField4 = new JTextField();
        // textField4.setPreferredSize(new Dimension(200, 2));
        textField4.setColumns(20);
        contentPanel.add(textField4);

        JLabel label5 = new JLabel("Perks:");
        label5.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        contentPanel.add(label5);
        JTextField textField5 = new JTextField();
        // textField5.setPreferredSize(new Dimension(200, 2));
        textField5.setColumns(20);
        contentPanel.add(textField5);

        final JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField1.getText());
                System.out.println(textField2.getText());
                System.out.println(textField3.getText());
                System.out.println(textField4.getText());
                System.out.println(textField5.getText());

                try {
                    // 1. Prepare the URL
                    String title = URLEncoder.encode(textField1.getText(), "UTF-8");
            String description = URLEncoder.encode(textField2.getText(), "UTF-8");
            String skills = URLEncoder.encode(textField3.getText(), "UTF-8");
            String salary = URLEncoder.encode(textField5.getText(), "UTF-8");
            String deadline = URLEncoder.encode(textField4.getText(), "UTF-8");
            
            // Construct the encoded URL
            String urlString1 = String.format("http://localhost:8080/ansh_singhal/job?title=%s&description=%s&skills=%s&salary=%s&deadline=%s",
                                              title, description, skills, salary, deadline);
                    String urlString2 = String.format("http://localhost:8080/ansh_singhal/jobApp?tableName=%s",title);
                    // String endpoint = "http://localhost:8080/ansh_singhal/job?title="+textField1.getText()+"&description="+textField2.getText()+"&skills="+textField3.getText()+"&salary="+textField5.getText()+"&deadline="+textField4.getText();
                    // String endpoint = "http://localhost:8080/ansh_singhal/job?title=Hackathon Team Member&description=We need a team member for our team in Smart India Hackathon 2024.&skills=React JS, Django&salary=There is a prize pool of 1 Lac.&deadline=1 week";
                    URL url1 = new URL(urlString1);
                    URL url2 = new URL(urlString2);
                    // 2. Open the connection
                    HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                    connection1.setRequestMethod("POST");
                    connection1.setDoOutput(true); // Enable sending a request body
                    HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                    connection2.setRequestMethod("POST");
                    connection2.setDoOutput(true); // Enable sending a request body
                    // 3. Set headers (if needed)
                    connection1.setRequestProperty("Content-Type", "application/json");
                    connection2.setRequestProperty("Content-Type", "application/json");
                    // 4. Prepare the request body (if needed)
                    String postData = "{ \"key1\": \"value1\", \"key2\": \"value2\" }"; // Example JSON
                    try (OutputStream os = connection1.getOutputStream()) {
                        os.write(postData.getBytes());
                    }
                    try (OutputStream os = connection2.getOutputStream()) {
                        os.write(postData.getBytes());
                    }

                    // 5. Get the response code
                    int responseCode1 = connection1.getResponseCode();
                    int responseCode2 = connection2.getResponseCode();
                    System.out.println("Job Posted Response Code: " + responseCode1);
                    System.out.println("Table Created Response Code: " + responseCode2);
                    // 6. Process the response (if needed)
                    if (responseCode2 == 200 || responseCode2 == 201) { // Success codes
                        // Read response using InputStream from connection
                    } else {
                        // Handle error
                        // try (BufferedReader in = new BufferedReader(
                        //     new InputStreamReader(connection1.getInputStream()))) {
                        // String inputLine;
                        // StringBuilder response = new StringBuilder();
                        // while ((inputLine = in.readLine()) != null) {
                        //     response.append(inputLine);
                        // }
                        // JSONArray jsonResponse = new JSONArray(response.toString());
                        // System.out.println(jsonResponse.toString());
        
                        try (BufferedReader in2 = new BufferedReader(
                            new InputStreamReader(connection2.getInputStream()))) {
                        String inputLine2;
                        StringBuilder response2 = new StringBuilder();
                        while ((inputLine2 = in2.readLine()) != null) {
                            response2.append(inputLine2);
                        }
                        JSONArray jsonResponse2 = new JSONArray(response2.toString());
                        System.out.println(jsonResponse2.toString());
                    }

                }
            // }
        } catch (Exception ex) {
                    // Handle exceptions (e.g., network errors)
                    ex.printStackTrace();
                }

                Window window = SwingUtilities.getWindowAncestor(submitButton);
                if (window != null) {
                    window.dispose();
                }
                if (prevWindow != null) {
                    prevWindow.dispose();
                }
                new HomeWindowIsLoginClient(username);
                // Main.main(null);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

}

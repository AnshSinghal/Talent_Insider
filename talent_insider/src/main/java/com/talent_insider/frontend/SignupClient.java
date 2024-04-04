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

public class SignupClient extends JFrame{
    SignupClient(){
        setTitle("Signup as a Client");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Name
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        nameField.setColumns(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        // User Name
        JPanel userNamePanel = new JPanel();
        JLabel userNameLabel = new JLabel("User Name: ");
        JTextField userNameField = new JTextField();
        userNameField.setColumns(20);
        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameField);
        // Password
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password: ");
        JTextField passwordField = new JTextField();
        passwordField.setColumns(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        // Email
        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        emailField.setColumns(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        // Description
        JPanel descriptionPanel = new JPanel();
        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField descriptionField = new JTextField();
        descriptionField.setColumns(20);
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionField);
        // Website
        JPanel websitePanel = new JPanel();
        JLabel websiteLabel = new JLabel("Website: ");
        JTextField websiteField = new JTextField();
        websiteField.setColumns(20);
        websitePanel.add(websiteLabel);
        websitePanel.add(websiteField);
        // Login Button
        JPanel loginButtonPanel = new JPanel();
        final JButton loginButton = new JButton("Signup");
        loginButtonPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // 1. Prepare the URL
                    String endpoint = "http://localhost:8080/ansh_singhal/companySignup?username="+ userNameField.getText() +"&password="+ passwordField.getText() +"&email="+ emailField.getText() +"&name="+ nameField.getText() +"&description="+ descriptionField.getText() +"&website=" + websiteField.getText();
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
                new HomeWindowIsLoginClient();
            }
        });
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(namePanel);
        mainPanel.add(userNamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(emailPanel);
        mainPanel.add(descriptionPanel);
        mainPanel.add(websitePanel);
        mainPanel.add(loginButtonPanel);

        add(mainPanel, BorderLayout.CENTER);
    }
}

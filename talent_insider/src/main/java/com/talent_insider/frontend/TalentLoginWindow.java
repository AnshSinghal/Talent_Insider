package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

public class TalentLoginWindow extends JFrame {
    TalentLoginWindow() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Talent Login");
        this.setVisible(true);

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
        // Phone Number
        JPanel numberPanel = new JPanel();
        JLabel numberLabel = new JLabel("Phone Number: ");
        JTextField numberField = new JTextField();
        numberField.setColumns(20);
        numberPanel.add(numberLabel);
        numberPanel.add(numberField);
        // Login Button
        JPanel loginButtonPanel = new JPanel();
        final JButton loginButton = new JButton("Login");
        loginButtonPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String name = "";
                String username = "";
                String password = "";
                String email = "";
                String number = "";

                try {

                    System.out.println(userNameField.getText());

                    // 1. Prepare the URL
                    String endpoint = "http://localhost:8080/ansh_singhal/login?name="+ "&username="+userNameField.getText() +"&password=&email=&number=";
                    URL url = new URL(endpoint);
                    System.out.println(endpoint);
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
                            // JTextArea textArea = new JTextArea(response.toString());
                            // JScrollPane scrollPane = new JScrollPane(textArea);
                            // JOptionPane.showMessageDialog(null, scrollPane);
                            System.out.println(response.toString());
                            JSONObject jsonResponse = new JSONObject(response.toString());
                            username = jsonResponse.getString("username");
                            password = jsonResponse.getString("password");
                            email = jsonResponse.getString("email");
                            number = jsonResponse.getString("number");
                            name = jsonResponse.getString("name");

                            System.out.println(jsonResponse);
                            
                            System.out.println("Name: " + name);
                            System.out.println("Username: " + username);
                            System.out.println("Password: " + password);
                            System.out.println("Email: " + email);
                            System.out.println("Number: " + number);
                            Window window = SwingUtilities.getWindowAncestor(loginButton);
                            if (window != null) {
                                window.dispose();
                            }
                            if (passwordField.getText().equals(password)) {
                                Window window1 = SwingUtilities.getWindowAncestor(loginButton);
                                if (window1 != null) {
                                    window1.dispose();
                                }
                                window.dispose();
                                System.out.println(name);
                                new HomeWindowIsLoginTalent(name);
                            } else {
                                JFrame errorFrame = new JFrame();
                        JPanel errorPanel = new JPanel();
                        JLabel errorLabel = new JLabel("Invalid Username or Password");
                        errorPanel.add(errorLabel);
                        errorFrame.add(errorPanel, BorderLayout.SOUTH);
                        errorFrame.setSize(200, 400);
                        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        errorFrame.setVisible(true);
                        System.out.println("Request failed. Response Code: " + responseCode);
                        new Main();
                            }
                        }
                    } else {
                        // Handle error
                        JFrame errorFrame = new JFrame();
                        JPanel errorPanel = new JPanel();
                        JLabel errorLabel = new JLabel("Invalid Username or Password");
                        errorPanel.add(errorLabel);
                        errorFrame.add(errorPanel, BorderLayout.SOUTH);
                        errorFrame.setSize(200, 200);
                        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        errorFrame.setVisible(true);
                        System.out.println("Request failed. Response Code: " + responseCode);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JFrame errorFrame = new JFrame();
                        JPanel errorPanel = new JPanel();
                        JLabel errorLabel = new JLabel("Invalid Username or Password");
                        JButton errorButton = new JButton("Try Again");
                        errorPanel.add(errorLabel);
                        errorFrame.add(errorButton, BorderLayout.SOUTH);
                        errorFrame.setSize(200, 400);
                        errorButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Window window = SwingUtilities.getWindowAncestor(errorButton);
                                if (window != null) {
                                    window.dispose();
                                }
                                new TalentLoginWindow();
                            }
                        });
                        errorFrame.add(errorPanel, BorderLayout.NORTH);
                        errorFrame.setSize(200, 200);
                        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        errorFrame.setVisible(true);
                        Window window = SwingUtilities.getWindowAncestor(loginButton);
                        if (window != null) {
                            window.dispose();
                        }
                        new Main();
                }

                Window window = SwingUtilities.getWindowAncestor(loginButton);
                if (window != null) {
                    window.dispose();
                }
                if (passwordField.getText().equals(password)) {
                    Window window1 = SwingUtilities.getWindowAncestor(loginButton);
                    if (window1 != null) {
                        window1.dispose();
                    }
                    // new ClientLoginWindow();
                } 
                // else {
                //     new HomeWindowIsLoginClient(userNameField.getText());
                // }
            }
        });

        // Signup Button
        JPanel signupButtonPanel = new JPanel();
        final JButton signupButton = new JButton("Signup Instead");
        signupButtonPanel.add(signupButton);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(signupButton);
                if (window != null) {
                    window.dispose();
                }
                new SignupTalent();
            }
        });

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));
        // mainPanel.add(namePanel);
        mainPanel.add(userNamePanel);
        mainPanel.add(passwordPanel);
        // mainPanel.add(emailPanel);
        // mainPanel.add(numberPanel);
        mainPanel.add(loginButtonPanel);
        mainPanel.add(signupButtonPanel);

        add(mainPanel, BorderLayout.CENTER);
    }
}

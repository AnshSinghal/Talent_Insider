package com.talent_insider.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import java.io.BufferedReader;
import java.net.URLEncoder;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GigsPanel extends JPanel {
    private Gig gig;
    boolean showViewMore;
    boolean showApply;
    String username;
    String name;
    public GigsPanel(Gig gig, boolean showViewMore, boolean showApply, String username, String name) {
        this.username = username;
        this.name = name;
        this.gig = gig;
        this.showViewMore = showViewMore;
        this.showApply = showApply;
        // setBackground(Color.BLUE);
        setLayout(new GridBagLayout());
        setBorder(new RoundedBorder(16));
        // this.setBackground(Color.BLUE);
        // setForeground(Color.BLUE);
        addJobTitleLabel();
        addDescriptionLabel();
        addSkillsLabel();
        addTimeExpectedLabel();
        addPaymentAmountLabel();
        if (showViewMore) addButton();
    }

    private void addButton() {
        JButton button = new JButton("View More");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // String name = "";
                // String number = "";
                // String email = "";
                // String age = "";
                // String bio = "";
                // String skills = "";
                // String experience = "";

                try {
                    // 1. Prepare the URL
                    String tableName = URLEncoder.encode(gig.getTitle(), "UTF-8");
                    // String endpoint = String.format("http://localhost:8080/ansh_singhal/jobData?tableName=%s",tableName);
                    String endpoint = "http://localhost:8080/ansh_singhal/jobDatas?tableName=" + tableName;
                    URL url = new URL(endpoint);
                    System.out.println("URL: " + url);
                    System.out.println("Endpoint: " + endpoint);
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

                          
                            JSONArray jsonResponse = new JSONArray(response.toString());
                            System.out.println(jsonResponse.toString());
                            new GigWindow(gig, showApply, username, name, true, jsonResponse);
                        }
                    } else {
                        // Handle error
                        System.out.println("Request failed. Response Code: " + responseCode);
                        // Handle error
                        try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        JSONArray jsonResponse = new JSONArray(response.toString());
                        System.out.println(jsonResponse.toString());
                    }}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(button, gbc);
    }

    private void addPaymentAmountLabel() {
        JLabel titleLabel = new JLabel(gig.getPaymentAmount());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(titleLabel, gbc);
    }

    private void addTimeExpectedLabel() {
        JLabel titleLabel = new JLabel(gig.getTimeExpected());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(titleLabel, gbc);
    }

    private void addSkillsLabel() {
        JLabel titleLabel = new JLabel(gig.getSkills());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(titleLabel, gbc);
    }

    private void addDescriptionLabel() {
        JLabel titleLabel = new JLabel(gig.getDescription());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(titleLabel, gbc);
    }

    private void addJobTitleLabel() {
        JLabel titleLabel = new JLabel(gig.getJobTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(titleLabel, gbc);
    }
}

class RoundedBorder implements Border {

    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 77));
        g2d.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
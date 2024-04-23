package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

public class Main {

    public static void main(String[] args) {

        // getGigs();

        ImageIcon imageIcon = new ImageIcon("talent_insider/src/main/java/com/talent_insider/frontend/talent.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        // navbar
        JPanel navbar = new JPanel();
        navbar.setBackground(new Color(0, 0, 77));
        navbar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        final JButton postReq = new JButton("Post a Requirement");
        final JButton talentLogin = new JButton("Login as a Talent");
        final JButton clientLogin = new JButton("Login as a Client");
        // navbar.add(postReq);
        navbar.add(talentLogin);
        navbar.add(clientLogin);

        postReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(postReq);
                if (window != null) {
                    window.dispose();
                }
                // new NewReq();
            }
        });

        talentLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(talentLogin);
                if (window != null) {
                    window.dispose();
                }
                new TalentLoginWindow();
            }
        });

        clientLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(clientLogin);
                if (window != null) {
                    window.dispose();
                }
                new ClientLoginWindow();
            }
        });

        // hero panel
        JLabel labelH1 = new JLabel("Talent Insider");
        labelH1.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel labelH2 = new JLabel("Getting together the best talents with the best clients");
        labelH2.setFont(new Font("Arial", Font.PLAIN, 16));
        labelH1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        labelH2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(labelH1);
        textPanel.add(labelH2);

        JPanel sideBySidePanel = new JPanel();
        sideBySidePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sideBySidePanel.add(new JLabel(imageIcon));
        sideBySidePanel.add(textPanel);
        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BorderLayout());
        heroPanel.add(sideBySidePanel, BorderLayout.WEST);

        List<GigsPanel> gigPanels = new ArrayList<>();
        for (int i = 0; i < getGigs().length(); i++) {
            GigsPanel gigPanel = new GigsPanel(new Gig(getGigs().getJSONObject(i).getString("title"), getGigs().getJSONObject(i).getString("description"), getGigs().getJSONObject(i).getString("skills"),
                    getGigs().getJSONObject(i).getString("deadline"), getGigs().getJSONObject(i).getString("salary")), false, false, "", "");
                    // gigPanel.setBackground(Color.BLUE);
                    gigPanels.add(gigPanel);
        }

        // Container for gigs
        JPanel gigsContainer = new JPanel();
        gigsContainer.setLayout(new GridLayout(2, 3));
        for (GigsPanel panel : gigPanels) {
            gigsContainer.add(panel);
        }

        // Main layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(navbar, BorderLayout.NORTH);
        mainPanel.add(heroPanel, BorderLayout.CENTER);
        mainPanel.add(gigsContainer, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Talent Insider");
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(mainPanel);
    }

    public static JSONArray getGigs(){
        try {
                    // 1. Prepare the URL
                    String endpoint = "http://localhost:8080/ansh_singhal/job?title=&description=&skills=&salary=&deadline=";
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
                            // JTextArea textArea = new JTextArea(response.toString());
                            // JScrollPane scrollPane = new JScrollPane(textArea);
                            // JOptionPane.showMessageDialog(null, scrollPane);

                            JSONArray jsonResponse = new JSONArray(response.toString());
                            // String name = jsonResponse.getString("name");
                            // String age = jsonResponse.getString("age");
                            // String bio = jsonResponse.getString("bio");
                            // String skills = jsonResponse.getString("skills");
                            // String experience = jsonResponse.getString("experience");

                            // System.out.println("Name: " + name);
                            // System.out.println("Age: " + age);
                            // System.out.println("Bio: " + bio);
                            // System.out.println("Skills: " + skills);
                            // System.out.println("Experience: " + experience);
                            System.out.println(jsonResponse.toString());
                            return jsonResponse;
                        }
                    } else {
                        // Handle error
                        System.out.println("Request failed. Response Code: " + responseCode);
                        return null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
    }
}
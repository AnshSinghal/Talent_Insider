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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

public class HomeWindowIsLoginTalent extends JFrame {

    private final String username;

    HomeWindowIsLoginTalent(String username) {
        this.username = username;
        this.setVisible(true);
        this.setTitle("Talent Insider");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("talent_insider/src/main/java/com/talent_insider/frontend/talent.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        // navbar
        JPanel navbar = new JPanel();
        navbar.setBackground(Color.GRAY);
        navbar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton postReq = new JButton("Post a Requirement");
        // JButton talentLogin = new JButton("Login as a Talent");
        // JButton clientLogin = new JButton("Login as a Client");
        final JButton profile = new JButton("Profile");
        // navbar.add(postReq);
        // navbar.add(talentLogin);
        // navbar.add(clientLogin);
        navbar.add(profile);

        postReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Window window = SwingUtilities.getWindowAncestor(postReq);
                // if (window != null) {
                // window.dispose();
                // }
                new NewReq();
            }
        });

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                int age = 0;
                String bio = "";
                String skills = "";
                String experience = "";
                try {
                    // 1. Prepare the URL
                    String endpoint = "http://localhost:8080/ansh_singhal/userProfile?name="+ username +"&age=&bio=&skills=&experience=";
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

                            JSONObject jsonResponse = new JSONObject(response.toString());
                            name = jsonResponse.getString("name");
                            age = jsonResponse.getInt("age");
                            bio = jsonResponse.getString("bio");
                            skills = jsonResponse.getString("skills");
                            experience = jsonResponse.getString("experience");
                            
                            System.out.println("Name: " + name);
                            System.out.println("Age: " + age);
                            System.out.println("Bio: " + bio);
                            System.out.println("Skills: " + skills);
                            System.out.println("Experience: " + experience);
                        }
                    } else {
                        // Handle error
                        System.out.println("Request failed. Response Code: " + responseCode);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Window window = SwingUtilities.getWindowAncestor(profile);
                // if (window != null) {
                // window.dispose();
                // }
                if (false) {
                    new CreateProfileTalent();
                } else {
                    new TalentProfileWindow((JFrame) window, name, age, bio, skills, experience);
                }
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
        for (int i = 0; i < Main.getGigs().length(); i++) {
            GigsPanel gigPanel = new GigsPanel(new Gig(Main.getGigs().getJSONObject(i).getString("title"), Main.getGigs().getJSONObject(i).getString("description"), Main.getGigs().getJSONObject(i).getString("skills"),
                    Main.getGigs().getJSONObject(i).getString("deadline"), Main.getGigs().getJSONObject(i).getString("salary")));
            gigPanels.add(gigPanel);
        }

        // Container for gigs
        JPanel gigsContainer = new JPanel();
        gigsContainer.setLayout(new GridLayout(2, 3));
        for (GigsPanel panel : gigPanels) {
            gigsContainer.add(panel);
        }
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(navbar, BorderLayout.NORTH);
        mainPanel.add(heroPanel, BorderLayout.CENTER);
        mainPanel.add(gigsContainer, BorderLayout.SOUTH);
        this.add(mainPanel);
    }
}

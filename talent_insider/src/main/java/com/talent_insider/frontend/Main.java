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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        ImageIcon imageIcon = new ImageIcon("talent_insider/src/main/java/com/talent_insider/frontend/talent.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        // navbar
        JPanel navbar = new JPanel();
        navbar.setBackground(Color.GRAY);
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
                new NewReq();
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
        for (int i = 0; i < 6; i++) {
            GigsPanel gigPanel = new GigsPanel(new Gig("Job Title " + i, "Description " + i, "Skills " + i,
                    "Time Expected " + i, "Payment Amount " + i));
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
}
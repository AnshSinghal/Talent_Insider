package com.talent_insider.frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ClientProfileWindow extends JFrame{
    private final JFrame previousWindow;
    ClientProfileWindow(JFrame previousWindow){
        this.previousWindow = previousWindow;
        this.setVisible(true);
        this.setTitle("Profile");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 1));

        // Name
        JLabel nameLabel = new JLabel("Name: " + getClientName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(nameLabel);

        // Username
        JLabel usernameLabel = new JLabel("Username: " + getClientUsername());
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(usernameLabel);

        // Description
        JLabel descriptionLabel = new JLabel("Description: " + getClientDescription());
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(descriptionLabel);

        // Email
        JLabel emailLabel = new JLabel("Email: " + getClientEmail());
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(emailLabel);

        // Website
        JLabel websiteLabel = new JLabel("Website: " + getClientWebsite());
        websiteLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(websiteLabel);

        // Logout Button Panel
        JPanel logoutButtonPanel = new JPanel();
        final JButton logoutButton = new JButton("Logout");
        final JFrame prevWindow = this.previousWindow;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    private String getClientWebsite() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getClientEmail() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getClientDescription() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getClientUsername() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }

    private String getClientName() {
        // TODO Auto-generated method stub
        return "Dummy Data";
    }
}

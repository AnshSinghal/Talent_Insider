import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientLoginWindow extends JFrame{
    ClientLoginWindow() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Client Login");
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
        JButton loginButton = new JButton("Login");
        loginButtonPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

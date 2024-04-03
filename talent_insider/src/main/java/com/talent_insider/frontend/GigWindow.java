package com.talent_insider.frontend;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class GigWindow extends JFrame {
    
    GigWindow(Gig gig) {
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
        detailsPanel.add(commentsTextArea);

        // Apply Button Panel
        JPanel applyButtonPanel = new JPanel();
        applyButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Apply Button
        final JButton applyButton = new JButton("Apply");
        applyButtonPanel.add(applyButton);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

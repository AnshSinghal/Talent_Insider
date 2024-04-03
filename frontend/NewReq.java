import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NewReq extends JFrame {
    public NewReq() {
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

        JLabel label5 = new JLabel("Payment Amount:");
        label5.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        contentPanel.add(label5);
        JTextField textField5 = new JTextField();
        // textField5.setPreferredSize(new Dimension(200, 2));
        textField5.setColumns(20);
        contentPanel.add(textField5);

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(submitButton);
                if (window != null) {
                    window.dispose();
                }
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

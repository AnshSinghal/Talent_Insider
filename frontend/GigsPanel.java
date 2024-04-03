import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GigsPanel extends JPanel {
    private Gig gig;

    public GigsPanel(Gig gig) {
        this.gig = gig;
        setLayout(new GridBagLayout());
        setBorder(new RoundedBorder(16));

        addJobTitleLabel();
        addDescriptionLabel();
        addSkillsLabel();
        addTimeExpectedLabel();
        addPaymentAmountLabel();
        addButton();
    }

    private void addButton() {
        JButton button = new JButton("View More");
        button.addActionListener(e ->{
            new GigWindow(gig);
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
        g2d.setColor(Color.GRAY);
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
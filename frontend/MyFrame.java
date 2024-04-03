import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
    MyFrame() {
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 0, 5));
    }
}

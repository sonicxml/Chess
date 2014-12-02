import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Instructions implements Runnable {
    public void run() {
        JFrame frame = new JFrame("Instructions");
        frame.setAlwaysOnTop(true);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        frame.getContentPane().add(new JLabel("YO YO YO YO YO BAGLES"), java.awt.BorderLayout.NORTH);
        frame.getContentPane().add(new JLabel("text field south"), java.awt.BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

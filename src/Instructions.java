import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Instructions implements Runnable {
    private final Dimension SIZE = new Dimension(300, 200);
    public void run() {
        JFrame frame = new JFrame("Instructions");
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(SIZE);
        frame.setMinimumSize(SIZE);
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);


        // Create header
        JLabel header = new JLabel("<html><h1> Instructions </h1></html>");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        
        // Create Instructions text
        JLabel body = new JLabel("<html><body><p> This game plays the same way as chess, yo. </p> <br> <br> <p> Wonder why? Because it IS chess, diggity dawg. </p></body></html>");
        body.setBorder(BorderFactory.createCompoundBorder(null,paddingBorder));

        // Create close button
        JButton close = new JButton("OK");
        final JFrame frameFinal = frame;
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameFinal.setVisible(false);
                frameFinal.dispose();
            }
        });
        close.setMaximumSize(new Dimension(30,20));
        frame.add(close, BorderLayout.SOUTH);
        frame.getContentPane().add(header, BorderLayout.NORTH);
        frame.getContentPane().add(body, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.requestFocus();
    }
}

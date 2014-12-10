import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

class PawnPromotion implements Runnable {
    private final Dimension SIZE = new Dimension(300, 250);
    public void run() {
        System.out.println("Hola como estas");
        final JDialog dialog = new JDialog(Game.frame, "Pawn Promotion", false);

        JButton[] buttons = {};
        JOptionPane optionPane = new JOptionPane(
                "Choose a piece to promote your pawn to:",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, buttons, null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Game.setPawnPromoString(event.getActionCommand());
                dialog.dispose();
            }
        };
        JButton queen = new JButton("Queen");
        queen.addActionListener(menuListener);
        panel.add(queen);
        JButton knight = new JButton("Knight");
        knight.addActionListener(menuListener);
        panel.add(knight);
        JButton rook = new JButton("Rook");
        rook.addActionListener(menuListener);
        panel.add(rook);
        JButton bishop = new JButton("Bishop");
        bishop.addActionListener(menuListener);
        panel.add(bishop);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        optionPane.add(panel, 1);

        dialog.getContentPane().add(optionPane);
        dialog.setVisible(true);
        Dimension size = new Dimension(300, 225);
        dialog.setSize(size);
        dialog.setPreferredSize(size);
        panel.setSize(size);
        optionPane.setSize(size);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(Game.frame);
    }
}

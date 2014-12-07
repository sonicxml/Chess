import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Game implements Runnable {
    private static final JMenuBar menu = new JMenuBar();
    private static final JMenu gameMenu = new JMenu("Game");
    private static final JMenuItem newGame = new JMenuItem("New Game");
    private static final JMenuItem undo = new JMenuItem("Undo");
    private static final JMenu helpMenu = new JMenu("Help");
    private static final JMenuItem instructions = new JMenuItem("Instructions");
    private static final JMenuItem about = new JMenuItem("About this game");

	public void run() {
		final JFrame frame = new JFrame("Chess");
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("White's move");
		status_panel.add(status);

		// Main playing area
		final ChessBoard court = new ChessBoard(status);
		frame.add(ChessBoard.frame, BorderLayout.CENTER);

		
		menu.setVisible(true);

		// Game Heading
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessBoard.reset();
				status.setText(ChessBoard.mesg.getText());
			}
		});
		gameMenu.add(newGame);
		
		undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChessBoard.undo();
            }
        });
        gameMenu.add(undo); 
        undo.setEnabled(false);
        
		// Help Heading
		instructions.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SwingUtilities.invokeLater(new Instructions());
		    }
		});
		about.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(frame,
		                "This game was made for a CIS 120 project.\n "
		                + "(C) Trevin Gandhi 2014",
		                "About this game",
		                JOptionPane.PLAIN_MESSAGE);
		    }
		});
		helpMenu.add(instructions);
		helpMenu.add(about);

		menu.add(gameMenu);
		menu.add(helpMenu);
		
	    frame.add(menu, BorderLayout.NORTH);

		// Put the frame on the screen
	    frame.setMinimumSize(new Dimension(800, 820));
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		ChessBoard.reset();
	}

	public static void toggleUndo(boolean bool) {
	    undo.setEnabled(bool);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}

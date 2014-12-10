import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;

class Game implements Runnable {
	static final JFrame frame = new JFrame("Chess");
	private static final JMenuBar menu = new JMenuBar();
    private static final JMenu gameMenu = new JMenu("Game");
    private static final JMenuItem newGame = new JMenuItem("New Game");
    private static final JMenuItem undo = new JMenuItem("Undo");
    private static final JMenu helpMenu = new JMenu("Help");
    private static final JMenuItem instructions = new JMenuItem("Instructions");
    private static final JMenuItem about = new JMenuItem("About this game");
	static String pawnPromoAnswer = "Queen";

	public void run() {
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("White's move");
		status_panel.add(status);

		// Main playing area
		final ChessBoard board = new ChessBoard(status);
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

	public static void endScreen(int winCon) {
		final JDialog dialog = new JDialog(frame, "Game Over", true);
		final JButton newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChessBoard.reset();
				dialog.dispose();
			}
		});

		final JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		String winner;
		if (winCon == 1) {
			winner = "White won!";
		} else if (winCon == -1) {
			winner = "Black won!";
		} else {
			winner = "Draw!";
		}
		JLabel header = new JLabel("<html><h1>" + winner + "</h1></html>");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.CENTER);

		JPanel panel = new JPanel();
		panel.add(header, BorderLayout.SOUTH);

		JButton[] buttons = {exit, newGame};
		JOptionPane optionPane = new JOptionPane(panel,
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null, buttons, newGame);
		dialog.getContentPane().add(optionPane);
		dialog.setSize(300, 150);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}

	public static void setPawnPromoString(String answer) {
		pawnPromoAnswer = answer;
	}

	public static String pawnPromoWrapper() {
//		try {
//			SwingUtilities.invokeLater(new PawnPromotion());
//		} catch (Exception e) {
//		}
		return pawnPromoAnswer;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
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
		frame.add(court.frame, BorderLayout.CENTER);

		final JMenuBar menu = new JMenuBar();
		menu.setVisible(true);

		// Game Heading
		JMenu gameMenu = new JMenu("Game");
		
		final JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
				status.setText(court.mesg.getText());
			}
		});
		gameMenu.add(newGame);
        gameMenu.add(new JMenuItem("Undo")); 

		// Help Heading
		JMenu helpMenu = new JMenu("Help");
		final JMenuItem instructions = new JMenuItem("Instructions");
		instructions.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SwingUtilities.invokeLater(new Instructions());
		    }
		});
		final JMenuItem about = new JMenuItem("About this game");
		helpMenu.add(instructions);
		helpMenu.add(about);

		menu.add(gameMenu);
		menu.add(helpMenu);
		
	    frame.add(menu, BorderLayout.NORTH);

		// Put the frame on the screen
	    frame.setMinimumSize(new Dimension(800, 820));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}

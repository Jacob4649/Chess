package chess.renderer.rules;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;

public class RulesWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void createRulesWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RulesWindow window = new RulesWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RulesWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel rules = new JLabel("<html>Chess is a two player game played on an 8 x 8 grid.<br>The game is played until one player puts the other in checkmate.<br>Checkmate is when a player cannot make any legal moves, and is in check.<br>Click on any piece to view all possible moves for that piece.<br>To make a move, just click on the highlighted square you want to end up on, with the piece you want to move selected.<br>The computer will make its move after you make yours.");
		rules.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rules.setVerticalAlignment(SwingConstants.TOP);
		rules.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(rules);
	}

}

package chess.renderer.menu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import chess.Chess;
import chess.renderer.RenderConstants;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		startMenu();
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton rulesButton = new JButton("Rules");
		rulesButton.setBounds(127, 229, 180, 23);
		frame.getContentPane().add(rulesButton);
		
		JLabel title = new JLabel("Jacob's Chess");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 40));
		title.setBounds(10, 11, 414, 45);
		frame.getContentPane().add(title);
		
		JComboBox colorSelector = new JComboBox(RenderConstants.PIECE_COLORS);
		colorSelector.setBounds(10, 230, 107, 20);
		frame.getContentPane().add(colorSelector);
		
		JLabel colorLabel = new JLabel("Color:");
		colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		colorLabel.setBounds(10, 205, 107, 14);
		frame.getContentPane().add(colorLabel);
		
		JComboBox layoutSelector = new JComboBox(RenderConstants.LAYOUT_OPTIONS);
		layoutSelector.setBounds(317, 230, 107, 20);
		frame.getContentPane().add(layoutSelector);
		
		JLabel layoutLabel = new JLabel("Layout:");
		layoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		layoutLabel.setBounds(317, 205, 107, 16);
		frame.getContentPane().add(layoutLabel);
		
		JButton playButton = new JButton("Play");
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		playButton.setBounds(10, 87, 414, 69);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new Chess(colorSelector.getSelectedItem().toString().equals("White"));
				if (layoutSelector.getSelectedItem().toString().equals("Classic")) 
					Chess.getBoard().setClassicLayout();
				else if (layoutSelector.getSelectedItem().toString().equals("Upside Down"))
					Chess.getBoard().setUpsideDownLayout();
				else if (layoutSelector.getSelectedItem().toString().equals("End Game"))
					Chess.getBoard().setEndgameLayout();
				else if (layoutSelector.getSelectedItem().toString().equals("Peasants"))
					Chess.getBoard().setPeasantsLayout();
				else if (layoutSelector.getSelectedItem().toString().equals("Brigade"))
					Chess.getBoard().setBrigadeLayout();
				Chess.firstMove();
				frame.dispose();
				
			}
		});
		frame.getContentPane().add(playButton);
	}
	
	/**
	 * Shows the main menu of the game
	 */
	public static void startMenu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

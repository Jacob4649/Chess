package chess;

import javax.swing.JFrame;

import chess.engine.board.Board;
import chess.engine.board.BoardState;
import chess.engine.opponent.Opponent;
import chess.renderer.RenderConstants;
import chess.renderer.panel.BoardPanel;

/**
 * Jacob Klimczak
 * ICS3UO
 * Course Summative - Chess Game
 * 
 * Info On AI Design And Minimaxing Was Taken From:
 * https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/
 * https://en.wikipedia.org/wiki/Minimax
 * 
 * No Code From Either Of These Sources Was Used
 */

/**
 * Represents a game of chess
 * @author Jacob
 *
 */
public class Chess {
	
	protected static Board m_board = new Board();
	protected static Opponent m_opponent = new Opponent();
	protected static BoardPanel m_boardPanel = new BoardPanel();
	
	/**
	 * Creates a new game of chess
	 * @param isWhite whether the player is white
	 */
	public Chess(boolean isWhite) {
		
		m_board = new Board();
		m_opponent = new Opponent();
		m_boardPanel = new BoardPanel();
		
		m_board.setPlayerIsWhite(isWhite);

		JFrame frame = new JFrame("Chess");
		
		frame.getContentPane().add(m_boardPanel);
		frame.pack();
		
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        frame.setSize(RenderConstants.WINDOW_HORIZONTAL, RenderConstants.WINDOW_VERTICAL);
        
        frame.setResizable(false);
		
        frame.setVisible(true);
			
	}
	
	/**
	 * gets the board
	 * @return m_board
	 */
	public static Board getBoard() {
		return m_board;
	}
	
	/**
	 * Gets the opponent
	 * @return m_opponent
	 */
	public static Opponent getOpponent() {
		return m_opponent;
	}
	
	/**
	 * Gets the boardPanel
	 * @return m_boardPanel
	 */
	public static BoardPanel getBoardPanel() {
		return m_boardPanel;
	}
	
	/**
	 * Has the computer make the first move if necessary
	 */
	public static void firstMove() {
		if (!m_board.getPlayerIsWhite()) { //have opponent make first move if player is white
			new Thread(new Runnable() { //allows UI to keep running
				public void run() {
					m_boardPanel.setMoveLock(true);
					m_opponent.takeTurn();
					m_boardPanel.setMoveLock(false);
				}
			}).start();
		}
	}
	
}

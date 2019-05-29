package chess;

import javax.swing.JFrame;

import chess.engine.board.Board;
import chess.engine.board.BoardState;
import chess.engine.opponent.Opponent;
import chess.renderer.RenderConstants;
import chess.renderer.panel.BoardPanel;

public class Chess {
	
	protected static Board m_board = new Board();
	protected static Opponent m_opponent = new Opponent();
	protected static BoardPanel m_boardPanel = new BoardPanel();
	
	Chess() {

		JFrame frame = new JFrame("Chess");
		
		frame.getContentPane().add(m_boardPanel);
		frame.pack();
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
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
	
	public static void main(String[] args) {
		new Chess();
	}
	
}

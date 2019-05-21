package chess;

import javax.swing.JFrame;

import chess.engine.board.Board;
import chess.renderer.RenderConstants;
import chess.renderer.panel.BoardPanel;

public class Chess {
	
	protected static Board m_board = new Board();
	
	Chess() {

		JFrame frame = new JFrame("Chess");
		
		BoardPanel board = new BoardPanel();
		
		frame.getContentPane().add(board);
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
	
	public static void main(String[] args) {
		new Chess();
	}
	
}

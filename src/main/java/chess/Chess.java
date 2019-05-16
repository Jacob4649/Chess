package chess;

import javax.swing.JFrame;

import chess.engine.board.Board;
import chess.renderer.RenderConstants;
import chess.renderer.panel.BoardPanel;

public class Chess {
	
	Chess() {

		JFrame frame = new JFrame("Chess");
		
		BoardPanel board = new BoardPanel(new Board());
		
		frame.getContentPane().add(board);
		frame.pack();
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        frame.setSize(RenderConstants.WINDOW_HORIZONTAL, RenderConstants.WINDOW_VERTICAL);
        
        frame.setResizable(false);
		
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Chess();
	}
	
}

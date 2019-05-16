package chess.renderer.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import chess.engine.EngineConstants;
import chess.engine.board.Board;
import chess.renderer.RenderConstants;

public class BoardPanel extends JPanel {

	protected Board m_board;
	
	/**
	 * Creates a new render panel that displays the board
	 * @param board the board to display
	 */
	public BoardPanel(Board board) {
		m_board = board;
		setBorder(BorderFactory.createLineBorder(RenderConstants.BOARD_BORDER_COLOR));
		setBackground(RenderConstants.BOARD_LIGHT_COLOR);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//paint tiles
		for (int hor = 0; hor < EngineConstants.BOARD_SIZE; hor++) { //renders from left to right 
			for (int vert = (hor % 2 == 0 ? 0 : 1); vert < EngineConstants.BOARD_SIZE; vert += 2) {
				g.fillRect(horizontalPosToInt(hor), verticalPosToInt(vert), ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
			}
		}
		
		//paint pieces
		for (int hor = 0; hor < EngineConstants.BOARD_SIZE; hor ++) {
			for (int vert = 0; vert < EngineConstants.BOARD_SIZE; vert++) {
				try {
					g.drawImage(m_board.getPieceAt(hor, vert).getImage(), horizontalPosToInt(hor), verticalPosToInt(vert), null); //draws piece
				} catch (Exception e) {
					//no piuece is at that position, or the resource file cannot be found
				}
				
			}
		}
		
		//repaints
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(RenderConstants.PANEL_HORIZONTAL, RenderConstants.PANEL_VERTICAL);
	}
	
	/**
	 * Converts an index of a horizontal position on the board to a point
	 * @param hor the index, 0-BoardSize to get the point for
	 * @return the pixel number for the specified point
	 */
	protected int horizontalPosToInt(int hor) {
		return ((int) (((double) hor/(double) EngineConstants.BOARD_SIZE)*RenderConstants.PANEL_HORIZONTAL));
	}
	
	/**
	 * Converts an index of a vertical position on the board to a point
	 * @param vert the index, 0-BoardSize to get the point for
	 * @return the pixel number for the specified point
	 */
	protected int verticalPosToInt(int vert) {
		return ((int) (((double) vert/(double) EngineConstants.BOARD_SIZE)*RenderConstants.PANEL_VERTICAL));
	}	
	
}

package chess.renderer.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import chess.Chess;
import chess.engine.EngineConstants;
import chess.engine.moves.Move;
import chess.engine.pieces.Piece;
import chess.renderer.RenderConstants;

public class BoardPanel extends JPanel {

	protected int[] m_mouseClickPos = new int[] {0, 0};
	protected Piece m_selectedPiece;
	
	/**
	 * Creates a new render panel that displays the board
	 * @param board the board to display
	 */
	public BoardPanel() {
		setBorder(BorderFactory.createLineBorder(RenderConstants.BOARD_BORDER_COLOR));
		setBackground(RenderConstants.BOARD_LIGHT_COLOR);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				m_selectedPiece = Chess.getBoard().getPieceAt(pixelToPos(e.getX(), e.getY())[0], pixelToPos(e.getX(), e.getY())[1]);
			}
		}); 
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//paint tiles
		for (int hor = 0; hor < EngineConstants.BOARD_SIZE; hor++) { //renders from left to right 
			for (int vert = (hor % 2 == 0 ? 0 : 1); vert < EngineConstants.BOARD_SIZE; vert += 2) {
				g.setColor(Color.BLACK);
				g.fillRect(horizontalPosToInt(hor), verticalPosToInt(vert), ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
			}
		}
		
		//paint pieces
		for (int hor = 0; hor < EngineConstants.BOARD_SIZE; hor ++) {
			for (int vert = 0; vert < EngineConstants.BOARD_SIZE; vert++) {
				try {
					g.drawImage(Chess.getBoard().getPieceAt(hor, vert).getImage(), horizontalPosToInt(hor), verticalPosToInt(vert), null); //draws piece
				} catch (Exception e) {
					//no piuece is at that position, or the resource file cannot be found
				}
				
			}
		}
		
		if (m_selectedPiece != null) { //if a piece is selected
			for (Move move : m_selectedPiece.getMoves()) {
				g.setColor(Color.RED);
				g.fillRect(horizontalPosToInt(move.getEndPosition()[0]), verticalPosToInt(move.getEndPosition()[1]), ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
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
	
	/**
	 * Gets the chess tile that the cursor is over
	 * @param hor the horizontal pixel
	 * @param vert the vertical pixel
	 * @return an array containing the horizontal and vertical indeces
	 */
	protected int[] pixelToPos(int hor, int vert) {
		return new int[] {(int) Math.floor(hor / (RenderConstants.PANEL_HORIZONTAL / EngineConstants.BOARD_SIZE)), (int) Math.floor(vert / (RenderConstants.PANEL_VERTICAL / EngineConstants.BOARD_SIZE))};
	}
	
}

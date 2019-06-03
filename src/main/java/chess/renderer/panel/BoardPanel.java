package chess.renderer.panel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Set;

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
	
	protected Move m_lastMove = null;
	protected boolean m_showLastMove = RenderConstants.SHOW_LAST_MOVE_DEFAULT;
	
	protected boolean m_moveLock = false;
	
	/**
	 * Creates a new render panel that displays the board
	 * @param board the board to display
	 */
	public BoardPanel() {
		setBorder(BorderFactory.createLineBorder(RenderConstants.BOARD_BORDER_COLOR));
		setBackground(RenderConstants.BOARD_LIGHT_COLOR);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				if (!m_moveLock) { //like a lock, blocks this code from running once the thread below is in progress
					m_moveLock = true;
					m_mouseClickPos = new int[] {e.getX(), e.getY()};
					new Thread(new Runnable() { //allows UI to keep functioning
						public void run() {
							int[] pos = pixelToPos(m_mouseClickPos[0], m_mouseClickPos[1]);
							
							Move move = null;
							
							if (m_selectedPiece != null) {
								move = m_selectedPiece.getMoveThatEndsAt(pos[0], pos[1]);
							} 
							
							if (move != null) {
								m_selectedPiece.move(move);
								m_selectedPiece = null;
								//opponent turn
								Chess.getOpponent().takeTurn();
							} else {
								m_selectedPiece = Chess.getBoard().getPieceAt(pos[0], pos[1]);
								if (m_selectedPiece != null && m_selectedPiece.getIsWhite() != Chess.getBoard().getPlayerIsWhite())
									m_selectedPiece = null;
							}
							
							m_moveLock = false;
						}
					}).start();
				}
			}
		}); 
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g.create();
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//paint tiles
		for (int hor = 0; hor < EngineConstants.BOARD_SIZE; hor++) { //renders from left to right 
			for (int vert = (hor % 2 == 0 ? 0 : 1); vert < EngineConstants.BOARD_SIZE; vert += 2) {
				g2D.setColor(Color.BLACK);
				g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
				g2D.fillRect(horizontalPosToInt(hor), verticalPosToInt(vert), ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
			}
		}
		
		//draws last move
		if (m_lastMove != null && m_showLastMove) {
			g2D.setColor(RenderConstants.LAST_MOVE_COLOR);
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g2D.setStroke(new BasicStroke((float) ((int) ((((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)) / 8.0)));
			g2D.drawLine(horizontalPosToInt(m_lastMove.getStartPosition()[0]) + (int) ((((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)/2.0), verticalPosToInt(m_lastMove.getStartPosition()[1]) + (int) ((((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)/2.0), horizontalPosToInt(m_lastMove.getEndPosition()[0]) + (int) ((((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)/2.0), verticalPosToInt(m_lastMove.getEndPosition()[1]) + (int) ((((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)/2.0));
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g2D.fill(new Ellipse2D.Double(horizontalPosToInt(m_lastMove.getStartPosition()[0]) + ((int) ((3.0 / 8.0) * (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE))), verticalPosToInt(m_lastMove.getStartPosition()[1]) +  + ((int) ((3.0 / 8.0) * (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE))), ((int) ((1.0 / 4.0) * (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE))), ((int) ((1.0 / 4.0) * (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)))));
		}

		//paint pieces
		for (int hor = 0; hor < EngineConstants.BOARD_SIZE; hor ++) {
			for (int vert = 0; vert < EngineConstants.BOARD_SIZE; vert++) {
				try {
					g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
					g2D.drawImage(Chess.getBoard().getPieceAt(hor, vert).getImage(), horizontalPosToInt(hor), verticalPosToInt(vert), null); //draws piece
				} catch (Exception e) {
					//no piece is at that position, or the resource file cannot be found
				}
				
			}
		}
		
		if (m_selectedPiece != null) { //if a piece is selected
			g2D.setColor(RenderConstants.SELECTED_PIECE_COLOR);
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));		
			g2D.fillRect(horizontalPosToInt(m_selectedPiece.getPosition()[0]), verticalPosToInt(m_selectedPiece.getPosition()[1]), ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
			for (Move move : m_selectedPiece.getMoves()) {
				g2D.setColor(RenderConstants.POTENTIAL_MOVE_COLOR);
				g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
				g2D.fillRect(horizontalPosToInt(move.getEndPosition()[0]), verticalPosToInt(move.getEndPosition()[1]), ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
			}
		}
		
		//paints text if it is opponent's turn
		if (Chess.getOpponent().getIsTurn()) {
			g2D.setFont(new Font(RenderConstants.FONT, Font.ITALIC, RenderConstants.FONT_SIZE));
			g2D.setColor(RenderConstants.BOARD_TEXT_BACKGROUND_COLOR);
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
			g2D.fillRect(0, ((int) (((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)), RenderConstants.PANEL_HORIZONTAL, ((int) (2.0 * ((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
			g2D.setColor(RenderConstants.BOARD_TEXT_COLOR);
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g2D.drawString(RenderConstants.OPPONENT_TURN_MESSAGE, ((int) (((double) RenderConstants.PANEL_HORIZONTAL)/(double) EngineConstants.BOARD_SIZE)), ((int) (2.0 * ((double) RenderConstants.PANEL_VERTICAL)/(double) EngineConstants.BOARD_SIZE)));
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
	
	/**
	 * Sets the last move to display onscreen
	 * @param move the move to display
	 */
	public void setLastMove(Move move) {
		m_lastMove = move;
	}
	
	/**
	 * assigns moveLock
	 * @param moveLock the value to assign
	 */
	public void setMoveLock(boolean moveLock) {
		m_moveLock = moveLock;
	}
	
}

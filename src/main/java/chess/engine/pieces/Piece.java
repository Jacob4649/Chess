package chess.engine.pieces;

import java.awt.Image;

/**
 * Class representing a piece
 * @author Jacob
 *
 */
public abstract class Piece {

	protected int m_value = 0;
	protected boolean m_isWhite = false;
	protected int[] m_position = new int[] {0, 0};
	
	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Piece(int value, boolean isWhite) {
		m_value = value;
		m_isWhite = isWhite;
	}
	
	public int getValue() {
		return m_value;
	}
	
	/**
	 * Sets the position of the piece
	 * @param xPosition the horizontal position to go to
	 * @param yPosition the vertical position to go to
	 */
	public void setPosition(int xPosition, int yPosition) {
		m_position = new int[] {xPosition, yPosition};
	}
	
	/**
	 * Gets the position of the piece
	 * @return an array containing the position of the piece
	 */
	public int[] getPosition() {
		return m_position;
	}
	
	/**
	 * Returns the image representing this piece
	 * @return the image to be drawn on screen to represent this piece
	 */
	public Image getImage() {
		return (m_isWhite ? m_whiteImage : m_blackImage);
	}
	
}

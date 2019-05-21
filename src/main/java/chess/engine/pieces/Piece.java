package chess.engine.pieces;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import chess.engine.moves.Move;
import chess.engine.moves.MoveTemplate;

/**
 * Class representing a piece
 * @author Jacob
 *
 */
public abstract class Piece {

	protected int m_value = 0;
	protected boolean m_isWhite = false;
	protected int[] m_position = new int[] {0, 0};
	
	protected ArrayList<MoveTemplate> m_possibleMoves;
	
	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Piece(int value, boolean isWhite, MoveTemplate[] moves) {
		m_value = value;
		m_isWhite = isWhite;
		m_possibleMoves = new ArrayList<MoveTemplate>(Arrays.asList(moves));
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
	
	/**
	 * Gets every possible move for this piece
	 * @return an array containing every possible move for this piece
	 */
	public Move[] getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (MoveTemplate template : m_possibleMoves) {
			for (Move move : template.getMoves(m_position[0], m_position[1])) {
				moves.add(move);
			}
		}
		return moves.toArray(new Move[moves.size()]);
	}
	
	
	//TODO : fix getPieceName
	public String getPieceName() {
		return toString().substring(toString().lastIndexOf('.')+1, toString().lastIndexOf('@'));
	}
	
}

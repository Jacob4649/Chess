package chess.engine.pieces;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import chess.Chess;
import chess.engine.board.Board;
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
	protected int m_moveCount = 0;
	
	protected ArrayList<MoveTemplate> m_possibleMoves;
	
	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Piece(int value, boolean isWhite, MoveTemplate[] moves) {
		m_value = value;
		m_isWhite = isWhite;
		m_possibleMoves = new ArrayList<MoveTemplate>(Arrays.asList(moves));
		if (!m_isWhite) { //flip y component on all move templates
			for (MoveTemplate move : m_possibleMoves) {
				move.setVerticalComponent(-move.getVerticalComponent());
			}
		}
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
	 * Gets the number of moves the piece has made
	 * @return the number of moves the piece has made
	 */
	public int getMoveCount() {
		return m_moveCount;
	}
	
	/**
	 * Increments the move count by 1
	 */
	public void incMoveCount() {
		m_moveCount++;
	}
	
	/**
	 * Executes the desired move
	 * @param move the move to execute
	 */
	public void move(Move move) {
		if (Chess.getBoard().getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]) != null) { //if a piece is at the end position
			Chess.getBoard().capture(Chess.getBoard().getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]));
		}
		
		Chess.getBoard().movePiece(this, move.getEndPosition()[0], move.getEndPosition()[1]);
		setPosition(move.getEndPosition()[0], move.getEndPosition()[1]);
		
		move.onMove();
		
		Chess.getBoardPanel().setLastMove(move);
		
		//TODO : consider getting rid of updatepositions, it is somewhat pointless if you can manage without it
		Chess.getBoard().updatePositions();
		
		Chess.getBoard().resetMoveStorage();
		
		m_moveCount++;
	}
	
	/**
	 * Gets a move this piece is capable of that ends at the specified position
	 * @param hor the horizontal index
	 * @param vert the vertical index
	 * @return the move that ends at the specified position, null if no move is found
	 */
	public Move getMoveThatEndsAt(int hor, int vert) {
		for (Move move : getMoves()) {
			if (move.getEndPosition()[0] == hor && move.getEndPosition()[1] == vert)
				return move;
		}
		return null;
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
		return getMoves(Chess.getBoard());
	}
	
	/**
	 * Gets every possible move for this piece
	 * @param board the board to check moves on
	 * @return an array containing every possible move for this piece
	 */
	public Move[] getMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (MoveTemplate template : m_possibleMoves) {
			for (Move move : template.getMoves(m_position[0], m_position[1], board)) {
				moves.add(move);
			}
		}
		return moves.toArray(new Move[moves.size()]);		
	}
	
	
	public String toString() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
	}
	
	/**
	 * Indicates whether a piece is white
	 * @return true if the piece is white
	 */
	public boolean getIsWhite() {
		return m_isWhite;
	}
	
	/**
	 * Configures every value on this object
	 * @param value the value of the piece
	 * @param isWhite the color of the piece
	 * @param possibleMoves the moveTemplates possible for this piece
	 * @param posx the horizontal index of this piece [0, 7]
	 * @param posy the vertical index of this piece [0, 7]
	 * @param moveCount the moveCount of this piece
	 * @retrun the piece that has been modified
	 */
	public Piece construct(int value, boolean isWhite, ArrayList<MoveTemplate> possibleMoves, int posx, int posy, int moveCount) {
		m_value = value;
		m_isWhite = isWhite;
		m_possibleMoves = possibleMoves;
		m_position = new int[] {posx, posy};
		m_moveCount = 0;
		return this;
	}
	
	/**
	 * Creates an identical clone of this piece
	 * @return an identical clone of this piece
	 */
	public abstract Piece clonePiece();	
}

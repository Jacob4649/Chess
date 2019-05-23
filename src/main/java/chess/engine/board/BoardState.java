package chess.engine.board;

import chess.Chess;
import chess.engine.moves.Move;
import chess.engine.pieces.Piece;

/**
 * Represents the state of a board at a specific time
 * @author Jacob
 *
 */
public class BoardState extends Board {

	protected int m_whiteValue = 0;
	protected int m_blackValue = 0;
	
	/**
	 * Creates a board state with a move applied to it
	 * @param board the board to start on
	 * @param move the move to apply to the board
	 */
	public BoardState(Board board, Move move) {
		m_piecePositions = board.getPiecePositions();
		
		if (move != null) {
			if (this.getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]) != null) { //if a piece is at the end position
				this.capture(this.getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]));
			}
			
			this.movePiece(this.getPieceAt(move.getStartPosition()[0], move.getStartPosition()[1]), move.getEndPosition()[0], move.getEndPosition()[1]);
			this.getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]).setPosition(move.getEndPosition()[0], move.getEndPosition()[1]);
			
			move.onMove();
			
			//TODO : consider getting rid of updatepositions, it is somewhat pointless if you can manage without it
			this.updatePositions();
			
			this.getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]).incMoveCount();
		}
		
		calculateValues();
	}
	
	/**
	 * Creates a board state representing a board
	 * @param board the board to represent
	 */
	public BoardState(Board board) {
		this(board, null);
	}
	
	/**
	 * Gets the total value of all pieces on the board
	 */
	public void calculateValues() {
		for (Piece[] row : m_piecePositions) {
			for (Piece piece : row) {
				if (piece != null) {
					if (piece.getIsWhite())
						m_whiteValue += piece.getValue();
					else
						m_blackValue += piece.getValue();
				}					
			}
		}

	}
	
	/**
	 * Gets the total white value for this board state
	 * @return m_whiteValue
	 */
	public int getWhiteValue() {
		return m_whiteValue;
	}
	
	/**
	 * Gets the total black value for this board state
	 * @return m_blackValue
	 */
	public int getBlackValue() {
		return m_blackValue;
	}
	
	/**
	 * Gets (whiteValue - blackValue)
	 * @return m_whiteValue - m_blackValue
	 */
	public int getValueDifference() {
		return m_whiteValue - m_blackValue;
	}
	
}

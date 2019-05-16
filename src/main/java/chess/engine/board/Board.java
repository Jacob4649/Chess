package chess.engine.board;

import chess.engine.EngineConstants;
import chess.engine.pieces.Pawn;
import chess.engine.pieces.Piece;

/**
 * Class representing a chess board
 * @author Jacob
 *
 */
public class Board {

	Piece[][] m_piecePositions = new Piece[EngineConstants.BOARD_SIZE][EngineConstants.BOARD_SIZE];
	
	/**
	 * Creates a new board with pieces in starting positions
	 */
	public Board() {
		for (int i = 0; i < EngineConstants.BOARD_SIZE; i++) { //lays out two rows of pawns
			m_piecePositions[i][1] = new Pawn(true);
			m_piecePositions[i][6] = new Pawn(false);
		}
	}
	
	/**
	 * Gets the piece at a specified position
	 * @param xPosition the x position to check at
	 * @param yPosition the y position to check at
	 * @return null if no piece is found, otherwise, the piece
	 */
	public Piece getPieceAt(int xPosition, int yPosition) {
		return m_piecePositions[xPosition][yPosition];
	}
	
}

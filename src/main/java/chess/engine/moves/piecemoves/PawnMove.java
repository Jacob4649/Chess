package chess.engine.moves.piecemoves;

import chess.engine.board.Board;
import chess.engine.moves.MoveTemplate;
import chess.engine.pieces.Piece;
import chess.engine.pieces.Queen;

/**
 * Standard move for a pawn
 * @author Jacob
 *
 */
public class PawnMove extends MoveTemplate {
	
	public PawnMove() {
		super(new int[] {0, 1}, false, false, false, false);
	}
	
	/**
	 * Calls onMove on a specific board
	 * @param board the board to call it on
	 * @param hot the horizontal index of the position of the piece calling onMove
	 * @param vert the vertical index of the position of the piece calling onMove
	 */
	@Override
	public void onMove(Board board, int hor, int vert) {
		Piece callingPiece = board.getPieceAt(hor, vert);
		if (callingPiece.getIsWhite()) {
			if (vert == 7) {
				board.setPieceAt(hor, vert, new Queen(true));
			}
		} else {
			if (vert == 0) {
				board.setPieceAt(hor, vert, new Queen(false));
			}
		}
	}
}

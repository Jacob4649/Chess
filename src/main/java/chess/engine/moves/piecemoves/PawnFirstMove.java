package chess.engine.moves.piecemoves;

import chess.engine.board.Board;
import chess.engine.moves.MoveTemplate;

/**
 * Represents the first move a pawn can make
 * @author Jacob
 *
 */
public class PawnFirstMove extends MoveTemplate {

	public PawnFirstMove() {
		super(new int[] {0, 2}, false, false, false, false);
	}
	
	
	/**
	 * Ensures that the piece has not yet moved
	 */
	@Override
	public boolean getConditions(int hor, int vert, Board board) {
		return board.getPieceAt(hor, vert).getMoveCount() == 0 && board.getPieceAt(hor, vert + (board.getPieceAt(hor, vert).getIsWhite() ? 1 : -1)) == null;
	}
	
}

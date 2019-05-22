package chess.engine.moves.piecemoves;

import chess.engine.moves.MoveTemplate;

/**
 * Standard move for a pawn
 * @author Jacob
 *
 */
public class PawnMove extends MoveTemplate {
	
	public PawnMove() {
		super(new int[] {0, 1}, false, false, false, false);
	}
	
}

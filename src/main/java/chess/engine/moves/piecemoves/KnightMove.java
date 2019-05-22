package chess.engine.moves.piecemoves;

import chess.engine.moves.MoveTemplate;

/**
 * Class representing a knight's basic move
 * @author Jacob
 *
 */
public class KnightMove extends MoveTemplate {
	
	public KnightMove() {
		super(new int[] {1, 2}, false, true, true, true);
	}
	
}

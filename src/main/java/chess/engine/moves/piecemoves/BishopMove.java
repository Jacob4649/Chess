package chess.engine.moves.piecemoves;

import chess.engine.moves.MoveTemplate;

/**
 * Class representing a bishop's basic move
 * @author Jacob
 *
 */
public class BishopMove extends MoveTemplate {
	
	public BishopMove() {
		super(new int[] {1, 1}, true, false, true, true);
	}
	
}

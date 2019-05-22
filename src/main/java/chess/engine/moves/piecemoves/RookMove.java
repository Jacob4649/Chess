package chess.engine.moves.piecemoves;

import chess.engine.moves.MoveTemplate;

/**
 * Class representing a rooks basic move
 * @author Jacob
 *
 */
public class RookMove extends MoveTemplate {

	public RookMove() {
		super(new int[] {1, 0}, true, true, true, true);
	}
	
}

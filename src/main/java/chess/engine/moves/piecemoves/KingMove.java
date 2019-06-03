package chess.engine.moves.piecemoves;

import chess.engine.moves.MoveTemplate;

/**
 * Represents the non diagonal moves a king makes
 * @author Jacob
 *
 */
public class KingMove extends MoveTemplate {
	public KingMove() {
		super(new int[] {0, 1}, false, false, true, true);
	}
}

package chess.engine.moves.piecemoves;

import chess.engine.moves.MoveTemplate;

/**
 * Represents the diagonal moves a king makes
 * @author Jacob
 *
 */
public class KingDiagonal extends MoveTemplate {
	public KingDiagonal() {
		super(new int[] {1, 1}, false, false, true, true);
	}
}

package chess.engine.pieces;

import javax.imageio.ImageIO;

import chess.engine.EngineConstants;
import chess.engine.moves.MoveTemplate;
import chess.engine.moves.piecemoves.KingDiagonal;
import chess.engine.moves.piecemoves.KingMove;
import chess.renderer.RenderConstants;

public class King extends Piece {

	public King(boolean isWhite) {
		super(EngineConstants.KING_VALUE, isWhite, new MoveTemplate[] {new KingMove(), new KingDiagonal()});
		
		if (m_blackImage == null || m_whiteImage == null) {
			try {
				m_whiteImage = ImageIO.read(getClass().getResource(RenderConstants.WHITE_KING_IMAGE));
				m_blackImage = ImageIO.read(getClass().getResource(RenderConstants.BLACK_KING_IMAGE));
			} catch (Exception e) {
				//resource not found
			}
		}
	}
	
	@Override
	public Piece clonePiece() {
		return (King) new King(getIsWhite()).construct(getValue(), getIsWhite(), m_possibleMoves, getPosition()[0], getPosition()[1], getMoveCount());
	}

}

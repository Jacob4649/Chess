package chess.engine.pieces;

import javax.imageio.ImageIO;

import chess.engine.EngineConstants;
import chess.engine.moves.MoveTemplate;
import chess.engine.moves.piecemoves.PawnCapturingMove;
import chess.engine.moves.piecemoves.PawnFirstMove;
import chess.engine.moves.piecemoves.PawnMove;
import chess.renderer.RenderConstants;

public class Pawn extends Piece {

	public Pawn(boolean isWhite) {
		super(EngineConstants.PAWN_VALUE, isWhite, new MoveTemplate[] {new PawnMove(), new PawnFirstMove(), new PawnCapturingMove()});
		
		try {
			m_whiteImage = ImageIO.read(getClass().getResource(RenderConstants.WHITE_PAWN_IMAGE));
			m_blackImage = ImageIO.read(getClass().getResource(RenderConstants.BLACK_PAWN_IMAGE));
		} catch (Exception e) {
			//resource not found
		}
	}
	
}

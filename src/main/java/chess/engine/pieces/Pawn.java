package chess.engine.pieces;

import java.awt.Image;

import javax.imageio.ImageIO;

import chess.engine.EngineConstants;
import chess.engine.moves.MoveTemplate;
import chess.engine.moves.piecemoves.PawnCapturingMove;
import chess.engine.moves.piecemoves.PawnFirstMove;
import chess.engine.moves.piecemoves.PawnMove;
import chess.renderer.RenderConstants;

/**
 * Class representing a pawn
 * @author Jacob
 *
 */
public class Pawn extends Piece {

	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Pawn(boolean isWhite) {
		super(EngineConstants.PAWN_VALUE, isWhite, new MoveTemplate[] {new PawnMove(), new PawnFirstMove(), new PawnCapturingMove()});
		
		if (m_blackImage == null || m_whiteImage == null) {
			try {
				m_whiteImage = ImageIO.read(getClass().getResource(RenderConstants.WHITE_PAWN_IMAGE));
				m_blackImage = ImageIO.read(getClass().getResource(RenderConstants.BLACK_PAWN_IMAGE));
			} catch (Exception e) {
				//resource not found
			}
		}
	}
	
	/**
	 * Returns the image representing this piece
	 * @return the image to be drawn on screen to represent this piece
	 */
	@Override
	public Image getImage() {
		return (m_isWhite ? m_whiteImage : m_blackImage);
	}

	/**
	 * Clones this piece
	 * @return a clone of this piece
	 */
	@Override
	public Pawn clonePiece() {
		return (Pawn) new Pawn(getIsWhite()).construct(getValue(), getIsWhite(), m_possibleMoves, getPosition()[0], getPosition()[1], getMoveCount());
	}
	
}

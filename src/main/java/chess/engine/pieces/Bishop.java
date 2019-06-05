package chess.engine.pieces;

import java.awt.Image;

import javax.imageio.ImageIO;

import chess.engine.EngineConstants;
import chess.engine.moves.MoveTemplate;
import chess.engine.moves.piecemoves.BishopMove;
import chess.renderer.RenderConstants;

/**
 * Class representing a bishop
 * @author Jacob
 *
 */
public class Bishop extends Piece {
	
	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Bishop(boolean isWhite) {
		super(EngineConstants.BISHOP_VALUE, isWhite, new MoveTemplate[] {new BishopMove()});
		
		if (m_blackImage == null || m_whiteImage == null) {
			try {
				m_whiteImage = ImageIO.read(getClass().getResource(RenderConstants.WHITE_BISHOP_IMAGE));
				m_blackImage = ImageIO.read(getClass().getResource(RenderConstants.BLACK_BISHOP_IMAGE));
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
	public Bishop clonePiece() {
		return (Bishop) new Bishop(getIsWhite()).construct(getValue(), getIsWhite(), m_possibleMoves, getPosition()[0], getPosition()[1], getMoveCount());
	}
}

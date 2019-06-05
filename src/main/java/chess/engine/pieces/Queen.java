package chess.engine.pieces;

import java.awt.Image;

import javax.imageio.ImageIO;

import chess.engine.EngineConstants;
import chess.engine.moves.MoveTemplate;
import chess.engine.moves.piecemoves.BishopMove;
import chess.engine.moves.piecemoves.RookMove;
import chess.renderer.RenderConstants;

/**
 * Class representing a queen
 * @author Jacob
 *
 */
public class Queen extends Piece {
	
	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Queen(boolean isWhite) {
		super(EngineConstants.QUEEN_VALUE, isWhite, new MoveTemplate[] {new BishopMove(), new RookMove()});
		
		if (m_blackImage == null || m_whiteImage == null) {
			try {
				m_whiteImage = ImageIO.read(getClass().getResource(RenderConstants.WHITE_QUEEN_IMAGE));
				m_blackImage = ImageIO.read(getClass().getResource(RenderConstants.BLACK_QUEEN_IMAGE));
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
	public Queen clonePiece() {
		return (Queen) new Queen(getIsWhite()).construct(getValue(), getIsWhite(), m_possibleMoves, getPosition()[0], getPosition()[1], getMoveCount());
	}
}

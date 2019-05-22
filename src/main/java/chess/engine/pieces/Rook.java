package chess.engine.pieces;

import java.awt.Image;

import javax.imageio.ImageIO;

import chess.engine.EngineConstants;
import chess.engine.moves.MoveTemplate;
import chess.engine.moves.piecemoves.RookMove;
import chess.renderer.RenderConstants;

/**
 * Class representing a rook
 * @author Jacob
 *
 */
public class Rook extends Piece {
	
	protected static Image m_blackImage;
	protected static Image m_whiteImage;
	
	public Rook(boolean isWhite) {
		super(EngineConstants.ROOK_VALUE, isWhite, new MoveTemplate[] {new RookMove()});
		
		try {
			m_whiteImage = ImageIO.read(getClass().getResource(RenderConstants.WHITE_ROOK_IMAGE));
			m_blackImage = ImageIO.read(getClass().getResource(RenderConstants.BLACK_ROOK_IMAGE));
		} catch (Exception e) {
			//resource not found
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
}

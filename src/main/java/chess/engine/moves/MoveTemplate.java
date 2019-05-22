package chess.engine.moves;

import java.util.ArrayList;

import chess.Chess;
import chess.engine.pieces.Piece;

/**
 * Represents a type of move that a piece can make
 * @author Jacob
 *
 */
public abstract class MoveTemplate {
	
	protected boolean m_rotateable = false; //whether the move vector can be rotated
	
	protected boolean m_swappable = false; //whether x and y on the move vector can be swapped
	
	protected boolean m_isInfinite = false; //indicates whether this move has a set range
	
	protected boolean m_capturing = false; //indicates if the move is allowed to capture
	
	protected int[] m_moveVector = new int[] {0, 0}; //vector representing the move
	
	protected static double[] m_moveStrengthArray; //array of the multiplier for piece values that this gives pieces
	
	/**
	 * Creates a new movetemplate
	 * @param vector the vector of the move
	 * @param infinite whether the move can be repeated indefinitely
	 * @param swappable whether the components of the vector can be swapped
	 * @param rotateable whether the vector can be rotated
	 * @param capturing whether the move can capture pieces
	 */
	public MoveTemplate(int[] vector, boolean infinite, boolean swappable, boolean rotateable, boolean capturing) {
		m_moveVector = vector;
		m_isInfinite = infinite;
		m_swappable = swappable;
		m_rotateable = rotateable;
		m_capturing = capturing;
	}
	
	/**
	 * Sets the X component of the template vector
	 * @param hor the value to assign to the x component
	 */
	public void setHorizontalComponent(int hor) {
		m_moveVector[0] = hor;
	}
	
	/**
	 * Sets the Y component of the template vector
	 * @param vert the value to assign to the y component
	 */
	public void setVerticalComponent(int vert) {
		m_moveVector[1] = vert;
	}
	
	/**
	 * Move is only allowed to be made when this method returns true
	 * @param hor the horizontal coordinate of the piece to check for
	 * @param vert the vertical coordinate of the piece to check moves for
	 * @return true if the move is currently allowed
	 */
	public boolean getConditions(int hor, int vert) {
		return true;
	}
	
	/**
	 * Method run immediately after the move
	 */
	public void onMove() {
		
	}
	
	/**
	 * Gets all possible permutations of this move from a position
	 * @param hor the x index [0 to (EngineConstants.BoardSize-1)]
	 * @param vert the y index [0 to (EngineConstants.BoardSize-1)]
	 * @return An array containing all possible moves
	 */
	public Move[] getMoves(int hor, int vert) { //gets all possible permutations of this move from a position
		ArrayList<Move> moves = new ArrayList<Move>();
		Piece callingPiece = Chess.getBoard().getPieceAt(hor, vert); //the piece calling the moveTemplate
		int[] templateVector = m_moveVector;
		
		for (int i = 0; i < (m_swappable ? 2 : 1); i++) { //if swappable, iterate through swaps
			
			for (int j = 0; j < (m_rotateable ? 4 : 1); j++) { //iterates through rotations
				
				int k = 1; //counter for infinite
				
				do {
					
					Move move = new Move(this, hor, vert, new int[] {k*templateVector[0], k*templateVector[1]});
					
					if (move.isValid() && getConditions(hor, vert)) {
						
						Piece piece = Chess.getBoard().getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]);
						
						if (piece != null ) { //if there is a peice in the way
							if (m_capturing && piece.getIsWhite() != callingPiece.getIsWhite()) //only if move is capturing and piece is opponent
								moves.add(move);
							break;
						} else {
							moves.add(move);
						}
						
					} else {
						break;
					}
						
					k++;
					
				} while (m_isInfinite); //iterates through infinite moves
				
				if (m_rotateable) { //rotate
					templateVector = new int[] {-templateVector[1], templateVector[0]};
				}
				
			}
			
			if (m_swappable) { //swap
				templateVector = new int[] {templateVector[1], templateVector[0]};
			}
		}
		
		return moves.toArray(new Move[moves.size()]);
	}
	
	/**
	 * Gets the horizontal component of the move vector
	 * @return the horizontal component
	 */
	public int getHorizontalComponent() {
		return m_moveVector[0];
	}
	
	/**
	 * Gets the vertical component of the move vector
	 * @return the vertical component
	 */
	public int getVerticalComponent() {
		return m_moveVector[1];
	}
	
}

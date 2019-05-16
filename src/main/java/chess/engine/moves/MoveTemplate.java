package chess.engine.moves;

import java.util.ArrayList;

/**
 * Represents a type of move that a piece can make
 * @author Jacob
 *
 */
public abstract class MoveTemplate {
	
	protected boolean m_rotateable = false; //whether the move vector can be rotated
	
	protected boolean m_swappable = false; //whether x and y on the move vector can be swapped
	
	protected boolean m_isInfinite = false; //indicates whether this move has a set range
	
	protected int[] m_moveVector = new int[] {0, 0}; //vector representing the move
	
	protected static double[] m_moveStrengthArray; //array of the multiplier for piece values that this gives pieces
	
	public MoveTemplate() {
		
	}
	
	/**
	 * Move is only allowed to be made when this method returns true
	 * @return true if the move is currently allowed
	 */
	public boolean getConditions() {
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
		int[] templateVector = m_moveVector;
		
		for (int i = 0; i < (m_swappable ? 2 : 1); i++) { //if swappable, iterate through swaps
			
			Move move = new Move(this, hor, vert, templateVector);
			
			if (move.isValid())
				moves.add(move); //adds move only if it is valid
			
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

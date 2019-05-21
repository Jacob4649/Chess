package chess.engine.moves;

/**
 * Represetns a move
 * @author Jacob
 *
 */
public class Move {

	protected MoveTemplate m_moveTemplate;
	protected int[] m_startPosition, m_endPosition, m_vector;
	
	/**
	 * Creates a new move
	 * @param move the move template
	 * @param hor the horizontal index
	 * @param vert the vertical index
	 */
	public Move(MoveTemplate move, int hor, int vert, int[] vector) {
		m_moveTemplate = move;
		m_startPosition = new int[] {hor, vert};
		m_vector = vector;
		m_endPosition = new int[] {m_startPosition[0] + m_vector[0], m_startPosition[1] + m_vector[1]};
	}
	
	/**
	 * Indicates whether the move is valid and keeps the piece within the games boundaries
	 * @return if the move is valid
	 */
	public boolean isValid() {
		return (m_endPosition[0] >= 0 && m_endPosition[0] <= 7 && m_endPosition[1] >= 0 && m_endPosition[1] <= 7); //checks if within board boundaries
	}
	
	/**
	 * Gets the end position of the move
	 * @return an array containing the end position of the move
	 */
	public int[] getEndPosition() {
		return m_endPosition;
	}
	
}

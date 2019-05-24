package chess.engine.moves;

import chess.engine.board.BoardState;

/**
 * Represents an entire move tree
 * @author Jacob
 *
 */
public class MoveTree {

	MoveTreeNode m_rootNode;
	
	/**
	 * Creates a move tree with the given parameters for the root node
	 * @param boardState the board state for the root node
	 * @param whiteTurn whether the move made on the first node is for a white turn
	 */
	public MoveTree(BoardState boardState, boolean whiteTurn) {
		m_rootNode = new MoveTreeNode(boardState, whiteTurn);
	}
	
	/**
	 * Gets all nodes in this tree at a specified depth
	 * @param depth the depth to search to
	 * @return an array of nodes at teh specified depth
	 */
	public MoveTreeNode[] getNodesAtDepth(int depth) {
		return m_rootNode.getNodesAtDepth(depth)
	}
	
}

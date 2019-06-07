package chess.engine.moves;

import java.util.ArrayList;
import java.util.Arrays;

import chess.Chess;
import chess.engine.board.BoardState;

/**
 * Represents an entire move tree
 * @author Jacob
 *
 */
public class MoveTree {

	protected MoveTreeNode m_rootNode;
	protected int m_depth;
	
	/**
	 * Creates a move tree with the given parameters for the root node
	 * @param boardState the board state for the root node
	 * @param whiteTurn whether the move made on the first node is for a white turn
	 */
	public MoveTree(BoardState boardState, int depth, boolean whiteTurn) {
		m_rootNode = new MoveTreeNode(boardState, this, whiteTurn);
		m_depth = depth;
		
		populateMoveTree();
		calculateCases(m_depth);
	}
	
	/**
	 * Gets all nodes in this tree at a specified depth
	 * @param depth the depth to search to
	 * @return an array of nodes at the specified depth
	 */
	public MoveTreeNode[] getNodesAtDepth(int depth) {
		return m_rootNode.getNodesAtDepth(depth);
	}
	
	/**
	 * Populates move tree
	 */
	public void populateMoveTree() {
		for (int i = 0; i < m_depth; i++) {
			for (MoveTreeNode node : getNodesAtDepth(i)) {
				node.addAllChildren();
			}
		}
	}
	
	/**
	 * Calculates cases
	 * @param depth the depth to search for cases down to
	 */
	public void calculateCases(int depth) {
		if (depth > m_depth)
			depth = m_depth;
		for (int i = depth; i >= 0; i--) {
			for (MoveTreeNode node : getNodesAtDepth(i)) {
				node.getBoardState().getWinConditions(!node.getIsWhiteTurn());
				if (node.getChildren().length > 0)
					node.setValue(node.getChildren()[0].getValue());
				else
					node.setValue(node.getBoardState().getValueDifference());
				for (MoveTreeNode child : node.getChildren()) { //assign worst possible value at each node
					if (node.getIsWhiteTurn() != Chess.getBoard().getPlayerIsWhite()) {
						//player children
						if (Chess.getBoard().getPlayerIsWhite()) { //white player (max)
							node.setValue(Math.max(node.getValue(), child.getValue()));
						} else { //black player (min)
							node.setValue(Math.min(node.getValue(), child.getValue()));
						}	
					} else {	
						//opponent children
						if (Chess.getBoard().getPlayerIsWhite()) { //white opponent (max)
							node.setValue(Math.min(node.getValue(), child.getValue()));
						} else { //black opponent (min)
							node.setValue(Math.max(node.getValue(), child.getValue()));
						}	
					}
				}
			}
		}
	}
	
	/**
	 * gets the depth of this tree
	 */
	public int getDepth() {
		return m_depth;
	}
	
}

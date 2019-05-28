package chess.engine.moves;

import java.util.ArrayList;
import java.util.Arrays;

import chess.engine.board.BoardState;

/**
 * Represents an entire move tree
 * @author Jacob
 *
 */
public class MoveTree {

	protected MoveTreeNode m_rootNode;
	protected ArrayList<ArrayList<MoveTreeNode>> m_treePyramid; //pyramid data structure for tree
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
	}
	
	/**
	 * Gets all nodes in this tree at a specified depth
	 * @param depth the depth to search to
	 * @return an array of nodes at the specified depth
	 */
	public MoveTreeNode[] getNodesAtDepth(int depth) {
		if (m_treePyramid == null) {
			m_treePyramid = new ArrayList<ArrayList<MoveTreeNode>>();
			m_treePyramid.add(new ArrayList<MoveTreeNode>(Arrays.asList(new MoveTreeNode[] {m_rootNode})));
		}
		
		if (m_treePyramid.size() < depth+1) {
			for (int i = m_treePyramid.size()-1; i < depth; i++) {
				m_treePyramid.add(new ArrayList<MoveTreeNode>());
				for (MoveTreeNode node : m_treePyramid.get(i)) {
					m_treePyramid.get(i+1).addAll(Arrays.asList(node.getChildren()));
				}
				m_treePyramid.remove(new ArrayList<MoveTreeNode>()); //removes if empty
			}
		}
		
		return m_treePyramid.get(depth).toArray(new MoveTreeNode[m_treePyramid.get(depth).size()]);
	}
	
	public void populateMoveTree() {
		for (int i = 0; i < m_depth; i++) {
			for (MoveTreeNode node : getNodesAtDepth(i)) {
				node.addAllChildren();
			}
		}
	}
	
}

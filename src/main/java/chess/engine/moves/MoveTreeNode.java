package chess.engine.moves;

import java.util.ArrayList;

import chess.engine.board.BoardState;

public class MoveTreeNode {

	protected int m_level;
	protected MoveTreeNode m_parent;
	protected BoardState m_info;
	protected Move m_move;
	protected boolean m_whiteTurn; //if this node is a white turn of a black turn
	ArrayList<MoveTreeNode> m_children = new ArrayList<MoveTreeNode>();
	
	/**
	 * Basic constructor for a moveTreeNode
	 * @param parent the parentNode
	 */
	public MoveTreeNode(MoveTreeNode parent) {
		m_parent = parent;
		if (parent != null)
			m_level = m_parent.getLevel() + 1;
		else
			m_level = 0;
	}
	
	/**
	 * Creates a childNode that applies a move to the boardstate of its parent
	 * @param parent the parent node
	 * @param move the move to apply
	 */
	public MoveTreeNode(MoveTreeNode parent, Move move) {
		this(parent);
		if (parent != null) {
			m_info = m_parent.getBoardState().spawnBoardState(move);
		}
		m_move = move;
	}
	
	/**
	 * Creates a root node for the move tree with a specific move
	 * @param info the boardState for the root node
	 */
	public MoveTreeNode(BoardState info) {
		this((MoveTreeNode) null);
		m_info = info;
		m_move = null;
	}
	
	/**
	 * Returns the level of the tree node
	 * @return m_level
	 */
	public int getLevel() {
		return m_level;
	}
	
	/**
	 * Gets the board state of the computer
	 * @return m_info
	 */
	public BoardState getBoardState() {
		return m_info;
	}
	
	/**
	 * returns the parent of this node;
	 * @return
	 */
	public MoveTreeNode getParent() {
		return m_parent;
	}
	
	/**
	 * Adds a child to this node
	 * @param child the child to add
	 */
	public void addChild(MoveTreeNode child) { //Usage : addChild(new MoveTreeNode(this, move));
		m_children.add(child);
	}
	
}

package chess.engine.moves;

import java.util.ArrayList;

import chess.engine.board.BoardState;

/**
 * Represents a single node on a move tree
 * @author Jacob
 *
 */
public class MoveTreeNode {

	protected int m_level, m_value = 0;
	protected MoveTreeNode m_parent;
	protected MoveTree m_tree;
	protected BoardState m_info;
	protected Move m_move;
	protected boolean m_whiteTurn; //if this node is a white turn or a black turn (move made on this node)
	ArrayList<MoveTreeNode> m_children = new ArrayList<MoveTreeNode>();
	
	/**
	 * Basic constructor for a moveTreeNode
	 * @param parent the parentNode
	 * @param whiteTurn whether this turn is white
	 */
	public MoveTreeNode(MoveTreeNode parent, boolean whiteTurn) {
		m_parent = parent;
		m_whiteTurn = whiteTurn;
		if (parent != null) {
			m_level = m_parent.getLevel() + 1;
			m_tree = parent.getTree();
		} else
			m_level = 0;
	}
	
	/**
	 * Creates a childNode that applies a move to the boardstate of its parent
	 * @param parent the parent node
	 * @param move the move to apply
	 */
	public MoveTreeNode(MoveTreeNode parent, Move move) {
		this(parent, !parent.getIsWhiteTurn());
		if (parent != null) {
			m_info = m_parent.getBoardState().spawnBoardState(move);
		}
		m_move = move;
	}
	
	/**
	 * Creates a root node for the move tree with a specific move
	 * @param info the boardState for the root node
	 * @param tree the moveTree containing this rootNode
	 * @param whiteTurn is this move white
	 */
	public MoveTreeNode(BoardState info, MoveTree tree, boolean whiteTurn) {
		this((MoveTreeNode) null, whiteTurn);
		m_info = info;
		m_tree = tree;
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
	 * Gets the color of this move
	 * @return m_whiteTurn
	 */
	public boolean getIsWhiteTurn() {
		return m_whiteTurn;
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
	
	/**
	 * Adds a child to this node
	 * @param move move to provide to the added child
	 */	
	public void addChild(Move move) {
		m_children.add(new MoveTreeNode(this, move));
	}
	
	/**
	 * Adds all child moves to the move tree
	 */
	public void addAllChildren() {
		for (Move move : getAllChildMoves()) {
			addChild(move);
		}
	}
	
	/**
	 * Gets a list of all available moves
	 * @return a list of all available moves for that 
	 */
	public Move[] getAllChildMoves() {
		if (m_whiteTurn) {
			return m_info.getBlackMoves();
		} else {
			return m_info.getWhiteMoves();
		}
	}
	
	/**
	 * Gets the moveTree containing this node
	 * @return m_tree
	 */
	public MoveTree getTree() {
		return m_tree;	
	}
	
	/**
	 * Gets the children of this node
	 * @return an array containing all children of this node
	 */
	public MoveTreeNode[] getChildren() {
		return m_children.toArray(new MoveTreeNode[m_children.size()]);
	}
	
	/**
	 * Sets the value
	 * @param value the value to assign
	 */
	public void setValue(int value) {
		m_value = value;
	}
	
	/**
	 * Gets the value case
	 * @return the value 
	 */
	public int getValue() {
		return m_value;
	}
	
	/**
	 * Gets the move that led to this node
	 * @return m_move
	 */
	public Move getMove() {
		return m_move;
	}
	
}

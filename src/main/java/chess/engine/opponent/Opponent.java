package chess.engine.opponent;

import chess.Chess;
import chess.engine.EngineConstants;
import chess.engine.moves.Move;
import chess.engine.moves.MoveTree;
import chess.engine.moves.MoveTreeNode;
import chess.renderer.RenderConstants;

/**
 * Class to represent the AI Opponent
 * @author Jacob
 *
 */
public class Opponent {
	
	protected boolean m_isTurn = false;
	
	public Opponent() {
	
	}
	
	/**
	 * The AI Opponent picks a move
	 * @return the move the opponent has selected
	 */
	public Move chooseMove() {
		
		MoveTree moves = new MoveTree(Chess.getBoard().spawnBoardState(), EngineConstants.AI_SEARCH_DEPTH, Chess.getBoard().getPlayerIsWhite());
		
		int selection = (int) (Math.random() * (moves.getNodesAtDepth(1).length - 1));
		boolean madeSelection = false;
		
		for (int i = 0; i < moves.getNodesAtDepth(1).length; i++) {
			if (Chess.getBoard().getPlayerIsWhite()) { //opponent is black
				if (moves.getNodesAtDepth(1)[i].getValue() < moves.getNodesAtDepth(1)[selection].getValue()) {
					selection = i;
					madeSelection = true;
				}
			} else { //opponent is white
				if (moves.getNodesAtDepth(1)[i].getValue() > moves.getNodesAtDepth(1)[selection].getValue()) {
					selection = i;
					madeSelection = true;
				}
			}
			
		}
		
		if (!madeSelection && (EngineConstants.ENABLE_CAPTURE_PRIORITISING || (EngineConstants.ENABLE_CAPTURE_PRIORITISING_IN_ENDGAME && Chess.getBoard().getIsEndgame()))) {
			for (int i = 0; i < moves.getNodesAtDepth(1).length; i++) {
				if (Chess.getBoard().getPlayerIsWhite()) { //opponent is black
					if (moves.getNodesAtDepth(1)[i].getBoardState().getValueDifference() < moves.getNodesAtDepth(1)[selection].getBoardState().getValueDifference()) {
						selection = i;
						madeSelection = true;
					}
				} else { //opponent is white
					if (moves.getNodesAtDepth(1)[i].getBoardState().getValueDifference() > moves.getNodesAtDepth(1)[selection].getBoardState().getValueDifference()) {
						selection = i;
						madeSelection = true;
					}
				}
			}
		}
		
		return moves.getNodesAtDepth(1)[selection].getMove();

	}
	
	/**
	 * Has the AI Opponent take his turn
	 */
	public void takeTurn() {
		
		m_isTurn = true;
		
		long moveStartTime = System.currentTimeMillis();
		
		Move move = chooseMove();
		
		long timeElapsed = System.currentTimeMillis() - moveStartTime;
		
		if (timeElapsed < RenderConstants.MINIMUM_MOVE_TIME) { //in place to ensure that the player can process the enemies move, makes the game more "readable"
			try {
				Thread.sleep(RenderConstants.MINIMUM_MOVE_TIME - timeElapsed);
			} catch (InterruptedException e) {
				
			}
		}
		
		Chess.getBoard().getPieceAt(move.getStartPosition()[0], move.getStartPosition()[1]).move(move);
		
		Chess.getBoard().getWinConditions(Chess.getBoard().getPlayerIsWhite());
		
		m_isTurn = false;
		
	}
	
	/**
	 * indicates if it is the opponents turn
	 * @return true if it is the opponents turn
	 */
	public boolean getIsTurn() {
		return m_isTurn;
	}
	
}

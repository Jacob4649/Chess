package chess.engine.opponent;

import chess.Chess;
import chess.engine.EngineConstants;
import chess.engine.moves.Move;
import chess.engine.moves.MoveTree;
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
		//TODO : finish move selection
		
		new MoveTree(Chess.getBoard().spawnBoardState(), EngineConstants.AI_SEARCH_DEPTH, !Chess.getBoard().getPlayerIsWhite());
		
		if (Chess.getBoard().getPlayerIsWhite()) { //opponent is black
			return Chess.getBoard().getBlackMoves()[(int) (Math.random() * (Chess.getBoard().getBlackMoves().length - 1))];
		} else { //opponent is white
			return Chess.getBoard().getWhiteMoves()[(int) (Math.random() * (Chess.getBoard().getWhiteMoves().length - 1))];
		}
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

package chess.engine.moves.piecemoves;

import java.util.ArrayList;
import java.util.Arrays;

import chess.Chess;
import chess.engine.moves.Move;
import chess.engine.moves.MoveTemplate;

/**
 * Move where a pawn captures
 * @author Jacob
 *
 */
public class PawnCapturingMove extends MoveTemplate {

	public PawnCapturingMove() {
		super(new int[] {1, 1}, false, false, true, true);
	}
	
	/**
	 * Gets all possible permutations of the pawn capture move from a position
	 * @param hor the x index [0 to (EngineConstants.BoardSize-1)]
	 * @param vert the y index [0 to (EngineConstants.BoardSize-1)]
	 * @return An array containing all possible moves
	 */
	@Override
	public Move[] getMoves(int hor, int vert) {
		ArrayList<Move> moves = new ArrayList<Move>(Arrays.asList(super.getMoves(hor, vert)));
		ArrayList<Move> removeMoves = new ArrayList<Move>();
		
		boolean white = Chess.getBoard().getPieceAt(hor, vert).getIsWhite();
		
		for (Move move : moves) {
			if (white) {
				if (move.getVector()[1] < 0 || Chess.getBoard().getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]) == null)
					removeMoves.add(move);
			} else {
				if (move.getVector()[1] > 0 || Chess.getBoard().getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]) == null)
					removeMoves.add(move);
			}
		}
		
		for (Move move : removeMoves) {
			moves.remove(move);
		}
		
		return moves.toArray(new Move[moves.size()]);
	}
	
	/**
	 * Move is only allowed to be made when this method returns true
	 * @param hor the horizontal coordinate of the piece to check for
	 * @param vert the vertical coordinate of the piece to check moves for
	 * @return true if the move is currently allowed
	 */
	@Override
	public boolean getConditions(int hor, int vert) {
		boolean white = Chess.getBoard().getPieceAt(hor, vert).getIsWhite();
		
		if (white) {
			return (Chess.getBoard().getPieceAt(hor+1, vert+1) != null || Chess.getBoard().getPieceAt(hor-1, vert+1) != null );
		} else {
			return (Chess.getBoard().getPieceAt(hor+1, vert-1) != null || Chess.getBoard().getPieceAt(hor-1, vert-1) != null );
		}
	}
	
}

package chess.engine.moves.piecemoves;

import java.util.ArrayList;

import chess.engine.board.Board;
import chess.engine.moves.Move;
import chess.engine.moves.MoveTemplate;
import chess.engine.pieces.King;
import chess.engine.pieces.Piece;
import chess.engine.pieces.Rook;

public class Castling extends MoveTemplate {
	public Castling() {
		super(new int[] {2, 0}, false, false, false, false);
	}
	
	/**
	 * Gets all possible permutations of the castling move from a position
	 * @param hor the x index [0 to (EngineConstants.BoardSize-1)]
	 * @param vert the y index [0 to (EngineConstants.BoardSize-1)]
	 * @return An array containing all possible moves
	 */
	@Override
	public Move[] getMoves(int hor, int vert, Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();
		Piece callingPiece = board.getPieceAt(hor, vert); //the piece calling the moveTemplate
		int[] templateVector = m_moveVector;

		Move[] rawMoves = null;
		
		if (callingPiece.getIsWhite()) { //white castle
			
			if ((board.getPieceAt(0, 0) instanceof Rook) && board.getPieceAt(0, 0).getMoveCount() == 0 && board.getPieceAt(0, 0).getIsWhite() == callingPiece.getIsWhite()) { //left
				if (rawMoves == null)
					rawMoves = board.getRawMoves(!callingPiece.getIsWhite());
				boolean canAdd = true;
				for (int i = 0; i < 3; i++) { //check space between
					if (board.getPieceAt(0 + (i+1), 0) != null)
						canAdd = false;
				}
				for (int i = 0; i < 2; i++) { //make sure no check
					for (Move move : board.getRawMoves(!callingPiece.getIsWhite())) {
						if (move.getEndPosition()[0] == (0 + (i+1)) && move.getEndPosition()[1] == 0)
							canAdd = false;
					}
				}
				if (canAdd) { //add move
					
				}
			} 
			if ((board.getPieceAt(7, 0) instanceof Rook) && board.getPieceAt(7, 0).getMoveCount() == 0 && board.getPieceAt(7, 0).getIsWhite() == callingPiece.getIsWhite()) { //right
				if (rawMoves == null)
					rawMoves = board.getRawMoves(!callingPiece.getIsWhite());
				boolean canAdd = true;
				for (int i = 0; i < 2; i++) { //check space between
					if (board.getPieceAt(7 - (i+1), 0) != null)
						canAdd = false;					
				}
				for (int i = 0; i < 2; i++) { //make sure no check
					for (Move move : board.getRawMoves(!callingPiece.getIsWhite())) {
						if (move.getEndPosition()[0] == (7 - (i+1)) && move.getEndPosition()[1] == 0)
							canAdd = false;
					}
				}
				if (canAdd) {
					
				}
			}
			
		} else { //black castle
			
			if ((board.getPieceAt(0, 7) instanceof Rook) && board.getPieceAt(0, 7).getMoveCount() == 0 && board.getPieceAt(0, 7).getIsWhite() == callingPiece.getIsWhite()) { //left
				if (rawMoves == null)
					rawMoves = board.getRawMoves(!callingPiece.getIsWhite());
				boolean canAdd = true;
				for (int i = 0; i < 3; i++) { //check space between
					if (board.getPieceAt(0 + (i+1), 7) != null)
						canAdd = false;
				}
				for (int i = 0; i < 2; i++) { //make sure no check
					for (Move move : board.getRawMoves(!callingPiece.getIsWhite())) {
						if (move.getEndPosition()[0] == (0 + (i+1)) && move.getEndPosition()[1] == 7)
							canAdd = false;
					}
				}
				if (canAdd) { //add move
					
				}
			} 
			if ((board.getPieceAt(7, 7) instanceof Rook) && board.getPieceAt(7, 7).getMoveCount() == 0 && board.getPieceAt(7, 7).getIsWhite() == callingPiece.getIsWhite()) { //right
				if (rawMoves == null)
					rawMoves = board.getRawMoves(!callingPiece.getIsWhite());
				boolean canAdd = true;
				for (int i = 0; i < 2; i++) { //check space between
					if (board.getPieceAt(7 - (i+1), 7) != null)
						canAdd = false;
				}
				for (int i = 0; i < 2; i++) { //make sure no check
					for (Move move : board.getRawMoves(!callingPiece.getIsWhite())) {
						if (move.getEndPosition()[0] == (7 - (i+1)) && move.getEndPosition()[1] == 7)
							canAdd = false;
					}
				}
				if (canAdd) { //add move
					
				}
			}
			
		}
		
		return moves.toArray(new Move[moves.size()]);
	}
	
	/**
	 * Indicates if castling is currently allowed
	 * @param hor the horizontal index to check if the move is allowed from
	 * @param vert the vertical index to check if the move is allowed from
	 * @param board the board to check if the move is allowed on
	 * @return true if the move is allowed
	 */
	@Override
	public boolean getConditions(int hor, int vert, Board board) {
		return board.getPieceAt(hor, vert) instanceof King && board.getPieceAt(hor, vert).getMoveCount() == 0 && !board.getInCheck(board.getPieceAt(hor, vert).getIsWhite());
	}
}

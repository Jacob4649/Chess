package chess.engine.board;

import java.util.ArrayList;

import chess.engine.EngineConstants;
import chess.engine.moves.Move;
import chess.engine.pieces.King;
import chess.engine.pieces.Knight;
import chess.engine.pieces.Piece;

/**
 * Represents the state of a board at a specific time
 * @author Jacob
 *
 */
public class BoardState extends Board {
	
	/**
	 * Creates a board state with a move applied to it
	 * @param board the board to start on
	 * @param move the move to apply to the board
	 */
	public BoardState(Board board, Move move) {
		for (int i = 0; i < EngineConstants.BOARD_SIZE; i++) {
			for (int j = 0; j < EngineConstants.BOARD_SIZE; j++) {
				if (board.getPieceAt(i, j) != null) {
					m_piecePositions[i][j] = board.getPieceAt(i, j).clonePiece();
					if (m_piecePositions[i][j].toString().equals("White King")) {
						m_whiteKing = (King) m_piecePositions[i][j];
					} else if (m_piecePositions[i][j].toString().equals("Black King")) {
						m_blackKing = (King) m_piecePositions[i][j];
					}
				} else
					m_piecePositions[i][j] = null;
			}
		}
		
		if (move != null) {
			if (getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]) != null) { //if a piece is at the end position
				capture(getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]));
			}
			
			movePiece(getPieceAt(move.getStartPosition()[0], move.getStartPosition()[1]), move.getEndPosition()[0], move.getEndPosition()[1]);
			getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]).setPosition(move.getEndPosition()[0], move.getEndPosition()[1]);
			
			move.onMoveOnBoard(this);
			
			//TODO : consider getting rid of updatePositions, it is somewhat pointless if you can manage without it
			updatePositions();
			
			getPieceAt(move.getEndPosition()[0], move.getEndPosition()[1]).incMoveCount();
		}
		
		calculateValues();
	}
	
	/**
	 * Creates a board state representing a board
	 * @param board the board to represent
	 */
	public BoardState(Board board) {
		this(board, null);
	}
	
	/**
	 * Prints the contents of this boardstate
	 * @param board the board to print
	 */
	void printBoard(Board board) {
		for (int i = 0; i < EngineConstants.BOARD_SIZE; i++) {
			System.out.print("\t");
			for (int j = 0; j < EngineConstants.BOARD_SIZE; j++) {
				System.out.print(("" + ((board.getPieceAt(j, i) instanceof Knight) ? "k" : (board.getPieceAt(j, i)).toString().charAt(0))));
			}
			System.out.println();
		}
	}
	
	/**
	 * Gets all possible black moves
	 * @return an array of potential black moves
	 */
	@Override
	public Move[] getBlackMoves() {
		//TODO : potential problem, unclear as of right now, may need to uncomment updatePositions, and remove the call below the first if statement
		//updatePositions();
		if (m_blackMoves != null)
			return m_blackMoves;
		updatePositions();
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece[] row : m_piecePositions) {
			for (Piece piece : row) {
				if (piece != null) {
					if (!piece.getIsWhite()) {
						for (Move move : piece.getMoves(this)) {
							moves.add(move);
						}
					}
				}
			}
		}
		m_blackMoves = moves.toArray(new Move[moves.size()]);
		return m_blackMoves;
	}
	
	/**
	 * Gets all possible white moves
	 * @return an array of potential white moves
	 */
	@Override
	public Move[] getWhiteMoves() {
		//updatePositions();
		if (m_whiteMoves != null)
			return m_whiteMoves;
		updatePositions();
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece[] row : m_piecePositions) {
			for (Piece piece : row) {
				if (piece != null) {
					if (piece.getIsWhite()) {
						for (Move move : piece.getMoves(this)) {
							moves.add(move);
						}
					}
				}
			}
		}
		m_whiteMoves = moves.toArray(new Move[moves.size()]);
		return m_whiteMoves;
	}
	
	/**
	 * Gets whether this board is in a stalemate or a checkmate
	 * @param whiteTurn if it was whites turn at the time of calling
	 * @return true if the board is in a stalemate or a checkmate
	 */
	@Override
	public boolean getWinConditions(boolean whiteTurn) {
		boolean end = super.getWinConditions(whiteTurn);
		if (end) {
			if (m_whiteCheckmate)
				m_blackValue += EngineConstants.POSITIVE_INFINITY;
			if (m_blackCheckmate)
				m_whiteValue += EngineConstants.POSITIVE_INFINITY;
		}
		return end;
	}
	
}

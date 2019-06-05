package chess.engine.board;

import java.util.ArrayList;

import chess.engine.EngineConstants;
import chess.engine.moves.Move;
import chess.engine.pieces.Bishop;
import chess.engine.pieces.King;
import chess.engine.pieces.Knight;
import chess.engine.pieces.Pawn;
import chess.engine.pieces.Piece;
import chess.engine.pieces.Queen;
import chess.engine.pieces.Rook;

/**
 * Class representing a chess board
 * @author Jacob
 *
 */
public class Board {

	protected Piece[][] m_piecePositions = new Piece[EngineConstants.BOARD_SIZE][EngineConstants.BOARD_SIZE];
	protected King m_blackKing, m_whiteKing;
	protected Move[] m_whiteMoves, m_blackMoves;
	protected ArrayList<Piece> m_whiteCaptured = new ArrayList<Piece>();
	protected ArrayList<Piece> m_blackCaptured = new ArrayList<Piece>();
	protected boolean m_playerIsWhite = true; 
	
	/**
	 * Creates a new board with pieces in starting positions
	 */
	public Board() {
		//TODO : fix starting layouts
		
		for (int i = 0; i < EngineConstants.BOARD_SIZE; i++) { //lays out two rows of pawns
			m_piecePositions[i][1] = new Pawn(true);
			m_piecePositions[i][6] = new Pawn(false);
		}
		
		m_piecePositions[1][0] = new Knight(true); //white knights
		m_piecePositions[6][0] = new Knight(true);
		
		m_piecePositions[1][7] = new Knight(false); //black knights
		m_piecePositions[6][7] = new Knight(false);
		
		m_piecePositions[0][0] = new Rook(true); //white rooks
		m_piecePositions[7][0] = new Rook(true);
		
		m_piecePositions[0][7] = new Rook(false); //black rooks
		m_piecePositions[7][7] = new Rook(false);
		
		m_piecePositions[2][0] = new Bishop(true); //white bishops
		m_piecePositions[5][0] = new Bishop(true);
		
		m_piecePositions[2][7] = new Bishop(false); //black bishops
		m_piecePositions[5][7] = new Bishop(false);
		
		m_blackKing = new King(false);
		m_whiteKing = new King(true);
		
		m_piecePositions[4][7] = m_blackKing; //black king
		m_piecePositions[3][0] = m_whiteKing; //white king
		
		m_piecePositions[3][7] = new Queen(false); //black queen
		m_piecePositions[4][0] = new Queen(true); //white queen
		
		updatePositions();
	}
	
	/**
	 * Called to set every pieces position to its actual position
	 */
	public void updatePositions() {
		for (int i = 0; i < EngineConstants.BOARD_SIZE; i++) {
			for (int j = 0; j < EngineConstants.BOARD_SIZE; j++) {
				Piece piece = getPieceAt(i, j);
				if (piece != null)
					piece.setPosition(i, j);
			}	
		}
	}
	
	/**
	 * Gets the piece at a specified position
	 * @param xPosition the x position to check at
	 * @param yPosition the y position to check at
	 * @return null if no piece is found, otherwise, the piece
	 */
	public Piece getPieceAt(int xPosition, int yPosition) {
		try {
			return m_piecePositions[xPosition][yPosition];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		
	}
	
	/**
	 * Captures a piece
	 * @param piece the piece to capture
	 */
	public void capture(Piece piece) {
		if (piece.getIsWhite()) { //adds to capture list
			m_whiteCaptured.add(piece);
		} else {
			m_blackCaptured.add(piece);
		}
		m_piecePositions[piece.getPosition()[0]][piece.getPosition()[1]] = null; //removes from board
	}
	
	/**
	 * Moves a piece to the specified position
	 * @param piece the piece to move
	 * @param hor the horizontal index
	 * @param vert the vertical index
	 */
	public void movePiece(Piece piece, int hor, int vert) {
		m_piecePositions[hor][vert] = piece;
		m_piecePositions[piece.getPosition()[0]][piece.getPosition()[1]] = null;
	}
	
	/**
	 * sets the color of the player
	 * @param white true - white, false - black
	 */
	public void setPlayerIsWhite(boolean white) {
		m_playerIsWhite = white;
	}
	
	/**
	 * gets the color of the player
	 * @return true if the player is white, false if the player is black
	 */
	public boolean getPlayerIsWhite() {
		return m_playerIsWhite;
	}
	
	/**
	 * Gets all possible raw moves
	 * @param isWhite the player to get raw moves for, tru if white
	 * @return an array of raw moves
	 */
	public Move[] getRawMoves(boolean isWhite) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece[] row : m_piecePositions) {
			for (Piece piece : row) {
				if (piece != null) {
					if (piece.getIsWhite() == isWhite) {
						for (Move move : piece.getRawMoves(this)) {
							moves.add(move);
						}
					}
				}
			}
		}
		return moves.toArray(new Move[moves.size()]);
	}
	
	/**
	 * Gets all possible black moves
	 * @return an array of potential black moves
	 */
	public Move[] getBlackMoves() {
		if (m_blackMoves != null)
			return m_blackMoves;
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece[] row : m_piecePositions) {
			for (Piece piece : row) {
				if (piece != null) {
					if (!piece.getIsWhite()) {
						for (Move move : piece.getMoves()) {
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
	public Move[] getWhiteMoves() {
		if (m_whiteMoves != null)
			return m_whiteMoves;
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece[] row : m_piecePositions) {
			for (Piece piece : row) {
				if (piece != null) {
					if (piece.getIsWhite()) {
						for (Move move : piece.getMoves()) {
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
	 * Gets all possible player moves
	 * @return an array of potential player moves
	 */
	public Move[] getPlayerMoves() {
		if (m_playerIsWhite) {
			return getWhiteMoves();
		} else {
			return getBlackMoves();
		}
	}
	
	/**
	 * Gets all possible computer moves
	 * @return an array of potential computer moves
	 */
	public Move[] getComputerMoves() {
		if (m_playerIsWhite) {
			return getBlackMoves();
		} else {
			return getWhiteMoves();
		}
	}
	
	/**
	 * Gets the positions of pieces on the board
	 * @return a 2D array containing all pieces on the board
	 */
	public Piece[][] getPiecePositions() {
		return m_piecePositions;
	}
	
	/**
	 * Spawns a new BoardState of the current board, with a single move applied to it
	 * @param move the move to apply
	 * @return a boardstate of this board, with the supplied move applied
	 */
	public BoardState spawnBoardState(Move move) {
		return new BoardState(this, move);
	}
	
	/**
	 * Spawns a new BoardState of the current board
	 * @return a boardstate of this board
	 */
	public BoardState spawnBoardState() {
		return new BoardState(this);
	}
	
	/**
	 * Gets either the white or the black king
	 * @param isWhite whether to get the white king or the black king
	 * @return the king of the specified color
	 */
	public King getKing(boolean isWhite) {
		return isWhite ? m_whiteKing : m_blackKing;
	}
	
	/**
	 * Indicates if the designated king is in check
	 * @param isWhite if true check white king, otherwise check black king
	 * @return true if the designated king is in check
	 */
	public boolean getInCheck(boolean isWhite) {
		updatePositions();
		for (Move move : getRawMoves(!isWhite)) {
			if (move.getEndPosition()[0] ==  (isWhite ? m_whiteKing : m_blackKing).getPosition()[0] && move.getEndPosition()[1] ==  (isWhite ? m_whiteKing : m_blackKing).getPosition()[1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the move storage, must be done after every turn
	 */
	public void resetMoveStorage() {
		m_blackMoves = null;
		m_whiteMoves = null;
	}
	
}

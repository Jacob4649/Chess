package chess.engine;

/**
 * Class containing all constants for the chess engine
 * @author Jacob
 *
 */
public class EngineConstants {

	//board values
	public static final int BOARD_SIZE = 8;
	
	//AI values
	public static final int AI_SEARCH_DEPTH = 3;
	
	//piece values
	public static final int PAWN_VALUE = 1;
	public static final int KNIGHT_VALUE = 10;
	public static final int ROOK_VALUE = 15;
	public static final int BISHOP_VALUE = 12;
	public static final int QUEEN_VALUE = 30;
	public static final int KING_VALUE = 900;
	
	public EngineConstants() {
		
	}
	
}

package chess.renderer;

import java.awt.Color;

/**
 * Class containing constants for the renderer
 * @author Jacob
 *
 */
public class RenderConstants {

	//sizes
	public static final int PANEL_HORIZONTAL = 600;
	public static final int PANEL_VERTICAL = 600;
	
	public static final int WINDOW_HORIZONTAL = 605;
	public static final int WINDOW_VERTICAL = 625;
	
	//times
	public static final int MINIMUM_MOVE_TIME = 1000;
	
	//colors
	public static final Color BOARD_BORDER_COLOR = Color.BLACK;
	public static final Color BOARD_DARK_COLOR = Color.BLACK;
	public static final Color BOARD_LIGHT_COLOR = Color.WHITE;
	public static final Color SELECTED_PIECE_COLOR = Color.BLUE;
	public static final Color POTENTIAL_MOVE_COLOR = Color.RED;
	public static final Color LAST_MOVE_COLOR = Color.RED;
	public static final Color BOARD_TEXT_BACKGROUND_COLOR = Color.GRAY;
	public static final Color BOARD_TEXT_COLOR = Color.WHITE;
	
	//misc
	public static final boolean SHOW_LAST_MOVE_DEFAULT = true;
	public static final String OPPONENT_TURN_MESSAGE = "Thinking...";
	public static final String WHITE_WIN_MESSAGE = "White Wins!";
	public static final String BLACK_WIN_MESSAGE = "Black Wins!";
	public static final String STALEMATE_MESSAGE = "Stalemate!";
	public static final String RETURN_TO_MENU_MESSAGE = "(Click On Any King To Return To The Menu)";
	public static final int FONT_SIZE = 75;
	public static final int SMALL_FONT_SIZE = 25;
	public static final String FONT = "Arial";
	public static final String[] PIECE_COLORS = new String[] {"White", "Black"};
	public static final String[] LAYOUT_OPTIONS = new String[] {"Classic", "Upside Down", "End Game", "Test"};
	
	//image locations
	public static final String WHITE_PAWN_IMAGE = "/resources/whitePawn.png";
	public static final String BLACK_PAWN_IMAGE = "/resources/blackPawn.png";
	public static final String WHITE_KNIGHT_IMAGE = "/resources/whiteKnight.png";
	public static final String BLACK_KNIGHT_IMAGE = "/resources/blackKnight.png";
	public static final String WHITE_ROOK_IMAGE = "/resources/whiteRook.png";
	public static final String BLACK_ROOK_IMAGE = "/resources/blackRook.png";
	public static final String WHITE_KING_IMAGE = "/resources/whiteKing.png";
	public static final String BLACK_KING_IMAGE = "/resources/blackKing.png";
	public static final String WHITE_BISHOP_IMAGE = "/resources/whiteBishop.png";
	public static final String BLACK_BISHOP_IMAGE = "/resources/blackBishop.png";
	public static final String WHITE_QUEEN_IMAGE = "/resources/whiteQueen.png";
	public static final String BLACK_QUEEN_IMAGE = "/resources/blackQueen.png";
	
	public RenderConstants() {
		
	}
	
}

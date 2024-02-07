
import application.UI;
import chess.ChessMatch;

public class App {
	public static void main(String[] args) {
		ChessMatch match = new ChessMatch();
		UI.printBoard(match.getPieces());
	}
}
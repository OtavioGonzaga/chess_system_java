import java.util.Scanner;

import application.UI;
import chess.ChessMatch;
import chess.ChessPosition;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ChessMatch match = new ChessMatch();

		while (true) {
			UI.printBoard(match.getPieces());
			System.out.println();
			System.out.print("\nSource: ");
			ChessPosition source = UI.readChessPosition(scanner);

			System.out.print("\nTarget: ");
			ChessPosition target = UI.readChessPosition(scanner);

			match.performChessMove(source, target);
			System.out.println();
		}
	}
}
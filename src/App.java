import java.util.InputMismatchException;
import java.util.Scanner;

import application.UI;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(match.getPieces());
				System.out.println();

				System.out.print("\nSource: ");
				ChessPosition source = UI.readChessPosition(scanner);

				UI.clearScreen();
				UI.printBoard(match.getPieces(), match.possibleMoves(source));

				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition(scanner);

				match.performChessMove(source, target);
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			}
		}
		// TODO:scanner.close();
	}
}
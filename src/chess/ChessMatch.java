package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
public class ChessMatch {
	private Board board = new Board(8, 8);

	public ChessMatch() {
		initialSetup();
	}

	private void placeChessPiece(char column, byte row, ChessPiece piece) {
		board.placePieces(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		//King and Queen
		placeChessPiece('d', (byte) 1, new King(board, Color.WHITE));
		placeChessPiece('d', (byte) 8, new King(board, Color.BLACK));
		placeChessPiece('e', (byte) 1, new Queen(board, Color.WHITE));
		placeChessPiece('e', (byte) 8, new Queen(board, Color.BLACK));
		// Initialize pawns
		for (byte i = 0; i < 16; i++) {
			board.placePieces(new Pawn(board, ((i < 8)? Color.BLACK : Color.WHITE)), new Position((i < 8)?  1 : 6, (i < 8) ? i : (i - 8)));
		}
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (byte i = 0; i < board.getRows(); i++) {
			for (byte j = 0; j < board.getColumns(); j++) {
				chessPieces[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return chessPieces;
	}
}

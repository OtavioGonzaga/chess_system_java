package chess;

import boardgame.Board;

public class ChessMatch {
	private Board board = new Board(8, 8);

	public ChessMatch() {
		//TODO:definir o construtor
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				chessPieces[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return chessPieces;
	}
}

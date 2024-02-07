package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board = new Board(8, 8);

	public ChessMatch() {
		initialSetup();
	}

	private void initialSetup() {
		// Initialize pawns
		for (byte i = 0; i < 2; i++) {
			for (byte j = 0; j < 8; j++) {
				byte color = (byte) ((i == 0) ? 1 : 6); // testa se é vez das peças brancas ou pretas, caso sejam as pretas, linha 2, caso sejam as brancas, linha 7
				board.boardPieces(new Pawn(board, ((color == 1)? Color.BLACK : Color.WHITE)), new Position(color, j));
			}
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

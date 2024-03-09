package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knigth;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private ArrayList<ChessPiece> onBoardPieces = new ArrayList<ChessPiece>();
	private ArrayList<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();
	private boolean check;

	// Constructor
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;

		initialSetup();
	}

	// moves
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		validateSourcePosition(source);
		validateTargetPosition(source, target);

		ChessPiece capturedPiece = makeMove(source, target);

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}

		check = testCheck(getOpponent(currentPlayer)) ? true : false;

		nextTurn();

		return capturedPiece;
	}

	private ChessPiece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		ChessPiece capturedPiece = (ChessPiece) board.removePiece(target);
		board.placePieces(p, target);

		if (capturedPiece != null) {
			onBoardPieces.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		return capturedPiece;
	}

	private void undoMove(Position source, Position target, ChessPiece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePieces(p, source);

		if (capturedPiece != null) {
			capturedPieces.remove(capturedPiece);
			onBoardPieces.add(capturedPiece);
		}

	}

	// validations
	private void validateSourcePosition(Position sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition))
			throw new ChessException("There is no piece on source position");
		if (!board.piece(sourcePosition).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move to the chosen piece");
		}
		if (!(((ChessPiece) board.piece(sourcePosition)).getColor() == currentPlayer))
			throw new ChessException("You cannot move a opponent's piece");
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target))
			throw new ChessException("The choiced piece can't move to the target position");
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);

		return board.piece(position).possibleMoves();
	}

	// Match config
	private void placeChessPiece(char column, byte row, ChessPiece piece) {
		board.placePieces(piece, new ChessPosition(column, row).toPosition());
		onBoardPieces.add(piece);
	}

	private boolean testCheck(Color color) {
		Position kingPosition = getKing(color).getChessPosition().toPosition();
		List<ChessPiece> opponentPieces = onBoardPieces.stream().filter(x -> x.getColor() == getOpponent(color))
				.collect(Collectors.toList());

		for (ChessPiece opponentPiece : opponentPieces) {
			boolean[][] possibleMoves = opponentPiece.possibleMoves();

			if (possibleMoves[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}

		return false;
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private void initialSetup() {
		// King and Queen
		placeChessPiece('d', (byte) 1, new King(board, Color.WHITE));
		placeChessPiece('d', (byte) 8, new King(board, Color.BLACK));
		placeChessPiece('e', (byte) 1, new Queen(board, Color.WHITE));
		placeChessPiece('e', (byte) 8, new Queen(board, Color.BLACK));
		// Bishop
		placeChessPiece('c', (byte) 1, new Bishop(board, Color.WHITE));
		placeChessPiece('f', (byte) 1, new Bishop(board, Color.WHITE));
		placeChessPiece('c', (byte) 8, new Bishop(board, Color.BLACK));
		placeChessPiece('f', (byte) 8, new Bishop(board, Color.BLACK));
		// Knigths
		placeChessPiece('b', (byte) 1, new Knigth(board, Color.WHITE));
		placeChessPiece('g', (byte) 1, new Knigth(board, Color.WHITE));
		placeChessPiece('b', (byte) 8, new Knigth(board, Color.BLACK));
		placeChessPiece('g', (byte) 8, new Knigth(board, Color.BLACK));
		// Rooks
		placeChessPiece('a', (byte) 1, new Rook(board, Color.WHITE));
		placeChessPiece('h', (byte) 1, new Rook(board, Color.WHITE));
		placeChessPiece('a', (byte) 8, new Rook(board, Color.BLACK));
		placeChessPiece('h', (byte) 8, new Rook(board, Color.BLACK));
		// Initialize pawns
		// for (byte i = 0; i < 16; i++) {
		// board.placePieces(new Pawn(board, ((i < 8) ? Color.BLACK : Color.WHITE)), new
		// Position((i < 8) ? 1 : 6, (i < 8) ? i : (i - 8)));
		// }
	}

	// getters and setters
	private Color getOpponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece getKing(Color color) {
		// return onBoardPieces.stream().filter(x -> x.getColor() == color).toArray(size
		// -> new ChessPiece[size])[0];
		List<ChessPiece> list = onBoardPieces.stream().filter(x -> x.getColor() == color).collect(Collectors.toList());

		for (ChessPiece chessPiece : list) {
			if (chessPiece instanceof King) {
				return chessPiece;
			}
		}

		throw new IllegalStateException("There's no king of color " + color + " in board");
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

	public boolean getCheck() {
		return check;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
}

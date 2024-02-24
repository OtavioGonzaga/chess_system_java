package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position auxPos = new Position(position.getRow() - 1, position.getColumn());

		// above
		while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
			mat[auxPos.getRow()][auxPos.getColumn()] = true;
			auxPos.setRow(auxPos.getRow() - 1);
		}

		if (getBoard().positionExists(auxPos) && thereIsOpponentpiece(auxPos))
			mat[auxPos.getRow()][auxPos.getColumn()] = true;

		// below
		auxPos.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
			mat[auxPos.getRow()][auxPos.getColumn()] = true;
			auxPos.setRow(auxPos.getRow() + 1);
		}

		if (getBoard().positionExists(auxPos) && thereIsOpponentpiece(auxPos))
			mat[auxPos.getRow()][auxPos.getColumn()] = true;

		// left
		auxPos.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
			mat[auxPos.getRow()][auxPos.getColumn()] = true;
			auxPos.setColumn(auxPos.getColumn() - 1);
		}

		if (getBoard().positionExists(auxPos) && thereIsOpponentpiece(auxPos))
			mat[auxPos.getRow()][auxPos.getColumn()] = true;

		// right
		auxPos.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
			mat[auxPos.getRow()][auxPos.getColumn()] = true;
			auxPos.setColumn(auxPos.getColumn() + 1);
		}

		if (getBoard().positionExists(auxPos) && thereIsOpponentpiece(auxPos))
			mat[auxPos.getRow()][auxPos.getColumn()] = true;

		return mat;
	}
}
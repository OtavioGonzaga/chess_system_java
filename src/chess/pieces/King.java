package chess.pieces;

import java.util.Scanner;

import application.UI;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position auxPos = new Position(position.getRow() - 1, position.getColumn() - 1);

		for (byte i = 0; i < 3; i++) {
			auxPos.setColumn(position.getColumn() - 1);
			for (byte j = 0; j < 3; j++) {
				if (getBoard().positionExists(auxPos) && (!getBoard().thereIsAPiece(auxPos) || thereIsOpponentpiece(auxPos)))
					mat[auxPos.getRow()][auxPos.getColumn()] = true;
				auxPos.setColumn(auxPos.getColumn() + 1);
			}
			auxPos.setRow(auxPos.getRow() + 1);
		}

		return mat;
	}

	@Override
	public String toString() {
		return "K";
	}
}
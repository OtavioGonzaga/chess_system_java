package chess;

import boardgame.Position;

public class ChessPosition {
	private char column;
	private byte row;

	public ChessPosition(char column, byte row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8)
			throw new ChessException("This position does not exist");
		this.column = column;
		this.row = row;
	}

	public ChessPosition(Position position) {
		if (position.getColumn() < 1 || position.getColumn() > 8 || position.getRow() < 1 || position.getRow() > 8)
			throw new ChessException("This position does not exist");
		this.column = toChessPosition(position).getColumn();
		this.row = toChessPosition(position).getRow();
	}

	protected Position toPosition() {
		return new Position(8 - this.row, this.column - 'a');
	}

	protected static ChessPosition toChessPosition(Position position) {
		return new ChessPosition((char) (position.getColumn() + 'a'), (byte) (8 - position.getRow()));
	}

	public char getColumn() {
		return column;
	}

	public byte getRow() {
		return row;
	}

	@Override
	public String toString() {
		return "" + column + row;
	}
}

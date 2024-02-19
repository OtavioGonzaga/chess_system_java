package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
	}

	public abstract boolean[][] possibleMoves(); 

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		for (int i = 0; i < possibleMoves().length; i++) {
			for (int j = 0; j < possibleMoves()[i].length; j++) {
				if (possibleMoves()[i][j] == true) return true;
			}
		}
		return false;
	}

	protected Board getBoard() {
		return board;
	}
}

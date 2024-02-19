package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	// Constructor
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) throw new BoardException("Error creating board: there must be at least one row and one column");

		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}
	
	// Functions
	public boolean thereIsAPiece(Position position) { // returns true if the position is full
		if (!positionExists(position)) throw new BoardException("Position does not exist");
		return piece(position) != null;
	}

	public void placePieces(Piece piece, Position position) { // place the piece at the position of argument
		if (thereIsAPiece(position)) throw new BoardException("There is already a piece at position " + position);
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean positionExists(int row, int column) {
		return row >= 0 && row <= this.rows && column >= 0 && column <= this.columns;
	}

	public Piece piece(int row, int column) { // return the piece on the position
		if (!positionExists(row, column)) throw new BoardException("Position does not exist");
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) throw new BoardException("Position does not exist");
		return pieces[position.getRow()][position.getColumn()];
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) throw new BoardException("Position does not exist");
		if (piece(position) == null) return null;
		Piece aux  = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	// Getters
	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
}

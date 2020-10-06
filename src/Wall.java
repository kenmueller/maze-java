public final class Wall {
	private final int row;
	private final int column;
	private final boolean isHorizontal;

	public Wall(final int row, final int column, final boolean isHorizontal) {
		this.row = row;
		this.column = column;
		this.isHorizontal = isHorizontal;
	}

	public int getRow() { return row; }
	public int getColumn() { return column; }
	public boolean isHorizontal() { return isHorizontal; }
}

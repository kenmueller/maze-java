public final class Cell {
	public static final int DIMENSION = 50;

	private boolean up = true;
	private boolean right = true;
	private boolean down = true;
	private boolean left = true;

	public void removeWallUp() { up = false; }
	public void removeWallRight() { right = false; }
	public void removeWallDown() { down = false; }
	public void removeWallLeft() { left = false; }

	public void display(final int row, final int column) {
		display(row, column, false);
	}

	public void display(final int row, final int column, final boolean displayBrokenWalls) {
		final int x = column * DIMENSION;
		final int y = row * DIMENSION;

		if (up != displayBrokenWalls)
			StdDraw.line(x, y, x + DIMENSION, y);

		if (right != displayBrokenWalls)
			StdDraw.line(x + DIMENSION, y, x + DIMENSION, y + DIMENSION);

		if (down != displayBrokenWalls)
			StdDraw.line(x, y + DIMENSION, x + DIMENSION, y + DIMENSION);

		if (left != displayBrokenWalls)
			StdDraw.line(x, y, x, y + DIMENSION);
	}
}

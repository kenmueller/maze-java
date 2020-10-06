/*
 * Display maze:
 *  - java Maze.java <rows> <columns>
 *
 * Display broken walls:
 *  - java Maze.java <rows> <columns> broken
 */

import java.util.Random;

public final class Maze {
	private static final Random random = new Random();

	private final int rows;
	private final int columns;

	private Wall[] walls;
	private Cell[][] cells;

	public Maze(final int rows, final int columns) {
		this.rows = rows;
		this.columns = columns;

		walls = new Wall[2 * rows * columns - rows - columns];
		cells = new Cell[rows][columns];

		int wallIndex = 0;

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new Cell();

				if (i < rows - 1)
					walls[wallIndex++] = new Wall(i, j, true);

				if (j < columns - 1)
					walls[wallIndex++] = new Wall(i, j, false);
			}
	}

	private void createOpenings() {
		cells[0][0].removeWallLeft();
		cells[rows - 1][columns - 1].removeWallRight();
	}

	public void generate() {
		final UnionFind unions = new UnionFind(rows * columns);

		createOpenings();

		while (unions.getCount() > 1) {
			final Wall wall = walls[random.nextInt(walls.length)];
			final boolean isHorizontal = wall.isHorizontal();

			final int row = wall.getRow();
			final int column = wall.getColumn();

			final int a = row * columns + column;
			final int b = isHorizontal
				? (row + 1) * columns + column
				: row * columns + column + 1;

			if (unions.isConnected(a, b))
				continue;

			if (isHorizontal) {
				cells[row][column].removeWallDown();
				cells[row + 1][column].removeWallUp();
			} else {
				cells[row][column].removeWallRight();
				cells[row][column + 1].removeWallLeft();
			}

			unions.union(a, b);
		}
	}

	public void display() {
		display(false);
	}

	public void display(final boolean displayBrokenWalls) {
		final int width = columns * Cell.DIMENSION;
		final int height = columns * Cell.DIMENSION;

		StdDraw.setCanvasSize(width + 1, height + 1);
		StdDraw.setPenRadius(0.02);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				cells[i][j].display(i, j, displayBrokenWalls);
	}

	public static void main(final String[] args) {
		if (args.length < 2)
			throw new RuntimeException("You must pass in a row and column count");

		final Maze maze = new Maze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

		maze.generate();
		maze.display(args.length > 2 && args[2].equalsIgnoreCase("broken"));
	}
}

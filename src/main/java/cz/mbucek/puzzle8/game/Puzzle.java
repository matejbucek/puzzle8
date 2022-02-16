package cz.mbucek.puzzle8.game;

import cz.mbucek.puzzle8.Main;
import cz.mbucek.puzzle8.general.Point;

public class Puzzle extends Shape{
	
	protected Grid grid;
	protected int width;
	protected int height;
	protected Point<Integer> offset;
	
	public Puzzle(Main main, int offsetX, int offsetY) {
		super(main, null);
		grid = new Grid(main, this, 3);
		width = main.width - Block.MODIFIER;
		height = main.height - Block.MODIFIER;
		offset = new Point<Integer>(offsetX, offsetY);
	}
	
	public void setGrid(int[][] grid) {
		this.grid = new Grid(main, this, 3, grid);
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	@Override
	public int getWidth() {
		return width - offset.x();
	}
	
	@Override
	public int getHeight() {
		return height - offset.y();
	}
	
	@Override
	public Point<Integer> getOffset() {
		return offset;
	}
	
	@Override
	public void draw() {
		grid.draw();
	}

	@Override
	public boolean isClicked(Point<Integer> point) {
		return grid.isClicked(point);
	}
}

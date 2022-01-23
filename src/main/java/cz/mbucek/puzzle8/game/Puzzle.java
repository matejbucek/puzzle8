package cz.mbucek.puzzle8.game;

import cz.mbucek.puzzle8.Main;
import cz.mbucek.puzzle8.general.Point;

public class Puzzle extends Shape{
	
	protected Grid grid;
	protected int width;
	protected int height;
	
	public Puzzle(Main main) {
		super(main, null);
		grid = new Grid(main, this, 3);
		width = main.width - Block.MODIFIER;
		height = main.height - Block.MODIFIER;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
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

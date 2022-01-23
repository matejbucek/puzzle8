package cz.mbucek.puzzle8.game;

import cz.mbucek.puzzle8.Main;
import cz.mbucek.puzzle8.general.MutablePoint;
import cz.mbucek.puzzle8.general.Point;
import cz.mbucek.puzzle8.logic.Solver;

public class Grid extends Shape{

	public static final int BACKGROUND = 200;
	protected Block[][] grid;
	
	public Grid(Main main, Shape parent, int size) {
		super(main, parent);
		grid = new Block[size][size];
		var originalGrid = Solver.generateSolvableGrid(size);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				grid[i][j] = new Block(main, this, new MutablePoint<Integer>(i, j), size, originalGrid[i][j]);
			}
		}
	}

	@Override
	public void draw() {
		main.background(BACKGROUND);
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				grid[i][j].draw();
			}
		}
	}
	
	private void moveToFreeSpaceIfPossible(Block block) {
		var point = block.getPosition();
		int x, y;
		//Up check
		if((point.x() - 1) >= 0 && grid[point.x() - 1][point.y()].getValue() == 0) {
			x = point.x() - 1;
			y = point.y();
			block.move(grid[x][y]);
			grid[x + 1][y] = grid[x][y]; 
			grid[x][y] = block;
		//Down check
		} else if((point.x() + 1) < grid.length && grid[point.x() + 1][point.y()].getValue() == 0) {
			x = point.x() + 1;
			y = point.y();
			block.move(grid[x][y]);
			grid[x - 1][y] = grid[x][y]; 
			grid[x][y] = block;
		//Left check
		} else if((point.y() - 1) >= 0 && grid[point.x()][point.y() - 1].getValue() == 0) {
			x = point.x();
			y = point.y() - 1;
			block.move(grid[x][y]);
			grid[x][y + 1] = grid[x][y]; 
			grid[x][y] = block;
		//Right check
		} else if((point.y() + 1) < grid.length && grid[point.x()][point.y() + 1].getValue() == 0) {
			x = point.x();
			y = point.y() + 1;
			block.move(grid[x][y]);
			grid[x][y - 1] = grid[x][y]; 
			grid[x][y] = block;
		}
	}

	@Override
	public boolean isClicked(Point<Integer> point) {
		var clicked = false;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(grid[i][j].isClicked(point)) {
					moveToFreeSpaceIfPossible(grid[i][j]);
				}
			}
		}
		return clicked;
	}
}

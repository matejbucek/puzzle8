package cz.mbucek.puzzle8.game;

import cz.mbucek.puzzle8.Main;
import cz.mbucek.puzzle8.general.MutablePoint;
import cz.mbucek.puzzle8.general.Point;
import cz.mbucek.puzzle8.logic.Solver;

public class Grid extends Shape{

	public static final int BACKGROUND = 200;
	protected Block[][] grid;
	
	public Grid(Main main, Shape parent, int size) {
		this(main, parent, size, Solver.generateSolvableGrid(size));
	}
	
	public Grid(Main main, Shape parent, int size, int[][] originalGrid) {
		super(main, parent);
		grid = new Block[size][size];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				grid[i][j] = new Block(main, this, new MutablePoint<Integer>(j, i), size, originalGrid[i][j]);
			}
		}
	}
	
	public int[][] getGrid(){
		var intGrid = new int[grid.length][grid.length];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				intGrid[i][j] = grid[i][j].getValue();
			}
		}
		return intGrid;
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
		if((point.y() - 1) >= 0 && grid[point.y() - 1][point.x()].getValue() == 0) {
			x = point.y() - 1;
			y = point.x();
			block.move(grid[x][y]);
			grid[x + 1][y] = grid[x][y]; 
			grid[x][y] = block;
		//Down check
		} else if((point.y() + 1) < grid.length && grid[point.y() + 1][point.x()].getValue() == 0) {
			x = point.y() + 1;
			y = point.x();
			block.move(grid[x][y]);
			grid[x - 1][y] = grid[x][y]; 
			grid[x][y] = block;
		//Left check
		} else if((point.x() - 1) >= 0 && grid[point.y()][point.x() - 1].getValue() == 0) {
			x = point.y();
			y = point.x() - 1;
			block.move(grid[x][y]);
			grid[x][y + 1] = grid[x][y]; 
			grid[x][y] = block;
		//Right check
		} else if((point.x() + 1) < grid.length && grid[point.y()][point.x() + 1].getValue() == 0) {
			x = point.y();
			y = point.x() + 1;
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
					System.out.println(grid[i][j].value);
					moveToFreeSpaceIfPossible(grid[i][j]);
				}
			}
		}
		return clicked;
	}
}

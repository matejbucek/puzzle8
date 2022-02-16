package cz.mbucek.puzzle8;

import cz.mbucek.puzzle8.game.Puzzle;
import cz.mbucek.puzzle8.general.Point;
import cz.mbucek.puzzle8.logic.Solver;
import processing.core.PApplet;

public class Main extends PApplet{

	private Puzzle puzzle;
	
	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}
	
	public void settings() {
		size(600, 600);
		puzzle = new Puzzle(this, 0, 0);
	}
	
	public void setup() {
		background(0);
	}
	
	public void draw() {
		puzzle.draw();
	}
	
	public void mouseClicked() {
		puzzle.isClicked(new Point<Integer>(mouseX, mouseY));
	}
	
	public void keyPressed() {
		//Generates solvable
		if(key == 'g') {
			var solvable = new int[][]{{1, 0, 5}, {2, 7, 4}, {3, 6, 8}};
			puzzle.setGrid(solvable);
		//Solve
		}else if(key == 's') {
			
		//Debug
		}else if(key == 'd') {
			var grid = puzzle.getGrid().getGrid();
			System.out.println(Solver.isGridSolvable(grid));
			Solver.printGrid(grid);
		}
	}
}

package cz.mbucek.puzzle8;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import cz.mbucek.puzzle8.game.Puzzle;
import cz.mbucek.puzzle8.general.Point;
import cz.mbucek.puzzle8.logic.AStar;
import cz.mbucek.puzzle8.logic.Node;
import cz.mbucek.puzzle8.logic.Solver;
import processing.core.PApplet;

public class Main extends PApplet{

	private Puzzle puzzle;
	private ThreadPoolExecutor executor;
	
	public static void main(String[] args) {
		//AStar aStar = new AStar(new Node(new int[][]{{1, 2, 3}, {0, 7, 6}, {5, 4, 8}}));
		//System.out.println(aStar.solve());
		PApplet.main(Main.class.getName());
	}
	
	public void settings() {
		size(600, 600);
		puzzle = new Puzzle(this, 0, 0);
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
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
			var solvable = new int[][]{{1, 2, 3}, 
									   {0, 7, 6}, 
									   {5, 4, 8}};
			puzzle.setGrid(solvable);
		//Solve
		}else if(key == 's') {
			System.out.println("Solve");
			executor.submit(() -> {
				var astar = new AStar(new Node(puzzle.getGrid().getGrid()));
				var path = astar.solve();
				System.out.println(path);
				puzzle.setGrid(path.get(0).getGrid());
				
			});
		//Debug
		}else if(key == 'd') {
			var grid = puzzle.getGrid().getGrid();
			System.out.println(Solver.isGridSolvable(grid));
			Solver.printGrid(grid);
		}
	}
}

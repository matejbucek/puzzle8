package cz.mbucek.puzzle8.logic;

import java.util.Random;

import cz.mbucek.puzzle8.general.Point;

public class Solver {

	private static Random random = new Random();

	public static int[][] generateRandomGrid(int size){
		var grid = new int[size][size];
		int counter = 1;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				grid[i][j] = counter++;
			}
		}

		grid[size - 1][size - 1] = 0;
		for(int i = 0; i < size; i++) {
			shuffle(grid);
		}

		return grid;
	}

	private static void shuffle(int[][] grid) {
		int tmp;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(random.nextBoolean()) {
					tmp = grid[i][j];
					grid[i][j] = grid[(i + 1) % grid.length][(i + 1) % grid.length];
					grid[(i + 1) % grid.length][(i + 1) % grid.length] = tmp;
				}
			}
		}
	}

	public static boolean isGridSolvable(int[][] grid) {
		int invCounter = 0;
		for (int i = 0; i < grid.length - 1; i++) {
			for (int j = i + 1; j < 3; j++) {
				if (grid[j][i] > 0 && grid[j][i] > grid[i][j]) invCounter++;
			}
		}
		//System.out.println(invCounter);
		//printGrid(grid);
		return invCounter % 2 == 0;
	}

	public static void printGrid(int[][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void repairGrid(int[][] grid) {
		int tmp;
		for (int i = 0; i < grid.length - 1; i++) {
			for (int j = i + 1; j < 3; j++) {
				if (grid[j][i] > 0 && grid[j][i] > grid[i][j]) {
					tmp = grid[i][j];
					grid[i][j] = grid[j][i];
					grid[j][i] = tmp;
					return;
				}
			}
		}
	}

	public static int[][] generateSolvableGrid(int size){
		int[][] grid = generateRandomGrid(size);

		while(!isGridSolvable(grid)) {
			repairGrid(grid);
		}

		return grid;
	}

	public static Point<Integer> find(int[][] grid, int x){
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(grid[i][j] == x) { 
					return new Point<Integer>(i, j);
				}
			}
		}
		return null;
	}


	private static boolean isSolved(int[][] actual, int[][] desired) {
		for(int i = 0; i < actual.length; i++) {
			for(int j = 0; j < actual.length; j++) {
				if(actual[i][j] != desired[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}

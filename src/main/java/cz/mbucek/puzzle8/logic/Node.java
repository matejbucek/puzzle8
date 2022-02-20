package cz.mbucek.puzzle8.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.mbucek.puzzle8.general.Point;

public class Node {

	private int[][] grid;
	private int depth;
	private Point<Integer> zero;
	private Node parent;
	
	public List<Node> getSuccessors(){
		var successors = new ArrayList<Node>();
		for(var move : BlockMove.values()) {
			var successor = move(move);
			if(successor != null) {
				successors.add(new Node(successor, this));
			}
		}
		return successors;
	}
	
	private int[][] move(BlockMove move) {
		int [][] copy = new int[grid.length][];
		for(int i = 0; i < grid.length; i++)
		    copy[i] = grid[i].clone();
		int x1 = zero.x(), y1 = zero.y(), x2 = zero.x(), y2 = zero.y();
		if(move == BlockMove.UP) {
			x2++;
		} else if(move == BlockMove.DOWN) {
			x2--;
		} else if(move == BlockMove.LEFT) {
			y2--;
		} else if(move == BlockMove.RIGHT) {
			y2++;
		}
		if(x2 >= 0 && x2 < copy.length && y2 >= 0 && y2 < copy.length) {
			var tmp = copy[x1][y1];
			copy[x1][y1] = copy[x2][y2];
			copy[x2][y2] = tmp;
			return copy;
		}
		return null;
	}
	
	private Node(int[][] grid, Node parent) {
		this(grid);
		this.parent = parent;
		this.depth = parent.depth + 1;
	}
	
	public Node(int[][] grid) {
		this.grid = grid;
		this.zero = Solver.find(grid, 0);
		this.depth = 0;
	}

	public int[][] getGrid() {
		return grid;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public Node getParent() {
		return parent;
	}
	
	@Override
	public int hashCode() {
		return Arrays.toString(grid).hashCode() + zero.x() * 5 + 93 * zero.y();
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();
		builder.append("[");
		for(int i = 0; i < grid.length; i++) {
			builder.append("[");
			for(int j = 0; j < grid.length; j++) {
				builder.append(grid[i][j] + ", ");
			}
			builder.delete(builder.length()-2, builder.length()-1);
			builder.append("]");
		}
		builder.append("]");
		return "Node [grid=" + builder.toString() + ", depth=" + depth + ", zero=" + zero 
				+ "]";
	}
	
	
}

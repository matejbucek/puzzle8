package cz.mbucek.puzzle8.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
	public static final int[][] DESIRED = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
	private PriorityQueue<Node> priorityQueue;
	private HashMap<Integer, Node> visited;
	private Node init;
	
	public AStar(Node init) {
		priorityQueue = new PriorityQueue<Node>(comparator);
		visited = new HashMap<Integer, Node>();
		this.init = init;
	}
	
	public List<Node> solve() {
		Node node = init;
		priorityQueue.add(node);
		while(!priorityQueue.isEmpty()) {
			node = priorityQueue.poll();
			visited.put(node.hashCode(), node);
			if(isSolved(node.getGrid(), DESIRED)) {
				var path = new ArrayList<Node>();
				while(!node.equals(init)) {
					path.add(node);
					node = node.getParent();
				}
				path.add(init);
				return path;
			}
			
			var successors = node.getSuccessors();
			for(var successor : successors) {
				if(!visited.containsKey(successor.hashCode()) && !priorityQueue.contains(successor)) {
					priorityQueue.add(successor);
				}
			}
		}
		return null;
	}
	
	private boolean isSolved(int[][] actual, int[][] desired) {
		for(int i = 0; i < actual.length; i++) {
			for(int j = 0; j < actual.length; j++) {
				if(actual[i][j] != desired[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private Comparator<Node> comparator = (Node a, Node b) -> (a.getDepth() + manhattan(a)) - (b.getDepth() + manhattan(b));
	
	private int heuristics(Node node) {
		int result = 0;
		int [][] state = node.getGrid();
		for(int i=0; i<state.length; i++) {
			for(int j=0; j<state.length; j++) {
				if(DESIRED[i][j]!=state[i][j]) {
					result += 1;
				}
			}
		}
		return result;
	}
	
	private int manhattan(Node node) {
		int result = 0;
		int [][]state = node.getGrid();
		for(int i=0; i<state.length; i++) {
			for(int j=0; j<state.length; j++) {
				int value = state[i][j];
				result += Math.abs(i - getRow(value)) + Math.abs(j - getCol(value)); 
			}
		}
		return result;
	}
	
	public int getRow(int value) {				//getting the Row of a value in goalState
		int row = 0;
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				if(DESIRED[i][j]==value) {
					row = i;
				}
			}
		}
			return row;
	}

	public int getCol(int value) {			//getting the Column of value in goal state used for Manhattan computation
		int col = 0;
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				if(DESIRED[i][j]==value) {
					col = j;
				}
			}
		}
			return col;
	}
}

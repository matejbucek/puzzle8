package cz.mbucek.puzzle8;

import cz.mbucek.puzzle8.game.Puzzle;
import cz.mbucek.puzzle8.general.Point;
import processing.core.PApplet;

public class Main extends PApplet{

	private Puzzle puzzle;
	
	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}
	
	public void settings() {
		size(600, 600);
		puzzle = new Puzzle(this);
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
}

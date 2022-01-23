package cz.mbucek.puzzle8.game;

import cz.mbucek.puzzle8.Main;
import cz.mbucek.puzzle8.general.Point;

public abstract class Shape {

	protected Main main;
	protected Shape parent;
	
	public Shape(Main main, Shape parent) {
		this.main = main;
		this.parent = parent;
	}
	
	public int getWidth() {
		return parent.getWidth();
	}
	
	public int getHeight() {
		return parent.getHeight();
	}
	
	public abstract void draw();
	public abstract boolean isClicked(Point<Integer> point);
}

package cz.mbucek.puzzle8.game;

import cz.mbucek.puzzle8.Main;
import cz.mbucek.puzzle8.general.MutablePoint;
import cz.mbucek.puzzle8.general.Point;

public class Block extends Shape{

	protected boolean isMoving;
	protected MutablePoint<Integer> current;
	protected MutablePoint<Integer> movement;
	protected int size;
	protected final int value;
	public static final int MODIFIER = 20;
	public static final int TEXT_SIZE = 50;
	
	public Block(Main main, Shape parent, MutablePoint<Integer> current, int size, int value) {
		super(main, parent);
		isMoving = false;
		this.current = current;
		this.size = size;
		this.value = value;
	}
	
	public void move(Block to) {
		var pos = to.getPosition();
		int x = current.x(), y = current.y();
		current.x(pos.x());
		current.y(pos.y());
		pos.x(x);
		pos.y(y);
	}
	
	private void blankDraw(MutablePoint<Integer> point) {
		if(value == 0) return;
		var actualX = getModifiedX(point);
		var actualY = getModifiedY(point);
		main.stroke(Grid.BACKGROUND);
		main.fill(Grid.BACKGROUND);
		main.rect(actualX.x(), actualY.x(), actualX.y(), actualY.y(), MODIFIER);
	}

	private void draw(MutablePoint<Integer> point) {
		var actualX = getModifiedX(point);
		var actualY = getModifiedY(point);
		main.stroke(100);
		main.fill(255);
		main.rect(actualX.x(), actualY.x(), actualX.y(), actualY.y(), MODIFIER);
		main.textSize(TEXT_SIZE);
		main.fill(0);
		main.text(value, actualX.x() + actualX.y() / 2 - TEXT_SIZE/4, actualY.x() + actualY.y() / 2 + TEXT_SIZE/4);
	}
	
	@Override
	public void draw() {
		if(value == 0) return;
		draw(current);
	}
	
	private MutablePoint<Integer> getModifiedX(MutablePoint<Integer> point){
		var actualX = (getWidth() / size) * point.x();
		return new MutablePoint<Integer>((MODIFIER / 3) * (point.x() + 1) + actualX, (getWidth() / size));
	}
	
	private MutablePoint<Integer> getModifiedY(MutablePoint<Integer> point){
		var actualY = (getHeight() / size) * point.y();
		return new MutablePoint<Integer>((MODIFIER / 3) * (point.y() + 1) + actualY, (getHeight() / size));
	}
	
	public MutablePoint<Integer> getPosition(){
		return current;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public boolean isClicked(Point<Integer> point) {
		var actualX = getModifiedX(current);
		var actualY = getModifiedY(current);
		return point.x() > actualX.x() && point.x() < actualX.x() + actualX.y() && point.y() > actualY.x() && point.y() < actualY.x() + actualY.y();
	}

	@Override
	public String toString() {
		return "Block [isMoving=" + isMoving + ", current=" + current + ", movement=" + movement + ", size=" + size
				+ ", value=" + value + "]";
	}

}

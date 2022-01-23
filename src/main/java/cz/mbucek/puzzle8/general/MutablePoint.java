package cz.mbucek.puzzle8.general;

public class MutablePoint<E> {

	protected E x;
	protected E y;
	
	public MutablePoint(E x, E y) {
		this.x = x;
		this.y = y;
	}
	
	public E x() {
		return x;
	}
	
	public E y() {
		return y;
	}
	
	public void x(E x) {
		this.x = x;
	}
	
	public void y(E y) {
		this.y = y;
	}
	
	public Point<E> immutable(){
		return new Point<E>(x, y);
	}

	@Override
	public String toString() {
		return "MutablePoint [x=" + x + ", y=" + y + "]";
	}
}

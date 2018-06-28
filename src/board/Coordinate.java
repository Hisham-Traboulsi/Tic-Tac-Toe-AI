package board;


public class Coordinate {
	public int x, y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "[ " + x + ", " + y + " ]";
	}
}

package shapes;

public class Square extends Rectangle {
	
	public Square() {
		super();
	}

	public Square(int x, int y, int size) {
		x1 = x;
		y1 = y;

		x2 = x1 + size;
		y2 = y1 + size;
	}

}

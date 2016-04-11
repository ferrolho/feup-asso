package shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Square extends Shape {

	public Square() {
		super();
	}

	public Square(int x, int y) {
		super(x, y);
	}

	public Square(int x, int y, int size, Color color) {
		super(x, y, size, color);
	}

	public Square(Square square) {
		super(square);
	}

	@Override
	public Shape copy() {
		return new Square(this);
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x - size / 2, y - size / 2, size, size);
	}

	@Override
	public void draw(Pixmap pixmap) {
		pixmap.setColor(color);
		pixmap.fillRectangle(x - size / 2, y - size / 2, size, size);
	}

}

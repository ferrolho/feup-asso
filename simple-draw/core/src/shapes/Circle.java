package shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Shape {

	public Circle() {
		super();
	}

	public Circle(int x, int y) {
		super(x, y);
	}

	public Circle(int x, int y, int size, Color color) {
		super(x, y, size, color);
	}

	public Circle(Shape shape) {
		super(shape);
	}

	@Override
	public Shape copy() {
		return new Circle(this);
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		shapeRenderer.circle(x, y, size);
	}

	@Override
	public void draw(Pixmap pixmap) {
		pixmap.setColor(color);
		pixmap.fillCircle(x, y, size);
	}

}

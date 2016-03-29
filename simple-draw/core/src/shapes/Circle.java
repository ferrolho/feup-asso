package shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle {

	private int x, y;
	private int radius;
	private Color color;

	public Circle() {
		x = y = 0;
		radius = 100;
		color = Color.BLACK;
	}

	public Circle(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		shapeRenderer.circle(x, y, radius);
	}

}

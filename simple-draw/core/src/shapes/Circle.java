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

	public Circle(Circle other) {
		this.x = other.x;
		this.y = other.y;
		this.radius = other.radius;
		this.color = other.color;
	}

	public Circle(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void changeSize(int amount) {
		this.radius += amount;
	}

	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		shapeRenderer.circle(x, y, radius);
	}

}

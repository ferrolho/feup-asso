package shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.feup.asso.SimpleDraw;

public abstract class Shape {

	protected int x, y;
	protected int size;
	public Color color;

	public Shape() {
		this.x = 0;
		this.y = 0;

		this.size = 50;

		this.color = Color.BLACK;
	}

	public Shape(int x, int y) {
		this.x = x;
		this.y = y;

		this.size = 50;

		this.color = Color.BLACK;
	}

	public Shape(int x, int y, int size, Color color) {
		this.x = x;
		this.y = y;

		this.size = size;

		this.color = color;
	}

	public Shape(Shape other) {
		this.x = other.x;
		this.y = other.y;

		this.size = other.size;

		this.color = other.color;
	}

	public abstract Shape copy();

	public abstract void draw(ShapeRenderer shapeRenderer);

	public abstract void draw(Pixmap pixmap);

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void changeSize(int amount) {
		this.size += amount * (this.size * 0.6);

		if (size < 2)
			size = 2;
		else if (size > Math.min(SimpleDraw.canvasSize.x, SimpleDraw.canvasSize.y) / 2)
			size = Math.min(SimpleDraw.canvasSize.x, SimpleDraw.canvasSize.y) / 2;
	}

	public void flipY() {
		this.y = SimpleDraw.canvasSize.y - this.y;
	}

}

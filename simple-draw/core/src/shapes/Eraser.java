package shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Eraser extends Shape {

	public Eraser() {
		super();
	}

	public Eraser(int x, int y) {
		super(x, y);
	}

	public Eraser(int x, int y, int size, Color color) {
		super(x, y, size, color);
	}

	public Eraser(Eraser eraser) {
		super(eraser);
	}

	@Override
	public Shape copy() {
		return new Eraser(this);
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(x, y, size);

		shapeRenderer.end();

		shapeRenderer.begin(ShapeType.Line);

		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(x, y, size);

		shapeRenderer.end();

		shapeRenderer.begin(ShapeType.Filled);
	}

	@Override
	public void draw(Pixmap pixmap) {
		pixmap.setColor(Color.WHITE);
		pixmap.fillCircle(x, y, size);
	}

}

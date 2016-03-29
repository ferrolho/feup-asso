package shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Rectangle {

	protected int x1, y1, x2, y2;
	protected Color color;

	public Rectangle() {
		x1 = y1 = 0;
		x2 = y2 = 100;
		
		color = Color.BLACK;
	}

	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x2;
		this.y2 = y2;
		
		this.color = color;
	}

	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x1, y1, x2 - x1, y2 - y1);
	}

}

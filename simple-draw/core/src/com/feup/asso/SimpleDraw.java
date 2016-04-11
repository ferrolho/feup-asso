package com.feup.asso;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import shapes.Circle;
import shapes.Eraser;
import shapes.Shape;
import shapes.Square;
import utilities.Coord;

public class SimpleDraw extends ApplicationAdapter implements InputProcessor {

	public static Coord canvasSize;
	private static Coord mouse;

	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;

	public Pixmap pixmap;
	public Texture texture;

	private ArrayList<Shape> drawing;
	private boolean redraw;

	private ActiveShapeEnum activeShapeEnum;
	private Shape activeShape;

	boolean ctrlIsBeingPressed = false;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);

		canvasSize = new Coord(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mouse = new Coord();

		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();

		pixmap = new Pixmap(canvasSize.x, canvasSize.y, Format.RGBA8888);
		pixmap.setColor(1, 1, 1, 1);
		pixmap.fillRectangle(0, 0, canvasSize.x, canvasSize.y);

		texture = new Texture(pixmap, true);

		drawing = new ArrayList<Shape>();
		redraw = false;

		activeShapeEnum = ActiveShapeEnum.NONE;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (redraw) {
			for (Shape shape : drawing)
				shape.draw(pixmap);

			texture.draw(pixmap, 0, 0);

			redraw = false;
		}

		// draw the canvas with the drawing
		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0);
		spriteBatch.end();

		// draw the active shape
		shapeRenderer.begin(ShapeType.Filled);
		if (activeShapeEnum != ActiveShapeEnum.NONE)
			activeShape.draw(shapeRenderer);
		shapeRenderer.end();
	}

	@Override
	public void dispose() {
		pixmap.dispose();
		texture.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.CONTROL_LEFT:
		case Input.Keys.CONTROL_RIGHT:
			ctrlIsBeingPressed = true;
			break;

		case Input.Keys.Z:
			if (ctrlIsBeingPressed) {
				if (!drawing.isEmpty())
					drawing.remove(drawing.size() - 1);
				else
					System.out.println("CTRL + Z - Nothing to undo!");

				pixmap.setColor(1, 1, 1, 1);
				pixmap.fillRectangle(0, 0, canvasSize.x, canvasSize.y);

				redraw = true;
			}
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.CONTROL_LEFT:
		case Input.Keys.CONTROL_RIGHT:
			ctrlIsBeingPressed = false;
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		switch (character) {
		case ' ':
			activeShapeEnum = ActiveShapeEnum.NONE;
			break;

		case 'c':
			activeShapeEnum = ActiveShapeEnum.CIRCLE;
			activeShape = new Circle(mouse.x, mouse.y);
			break;

		case 'e':
			activeShapeEnum = ActiveShapeEnum.ERASER;
			activeShape = new Eraser(mouse.x, mouse.y);
			break;

		case 's':
			activeShapeEnum = ActiveShapeEnum.SQUARE;
			activeShape = new Square(mouse.x, mouse.y);
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		updateShape(screenX, screenY);
		addShape();

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		updateShape(screenX, screenY);
		addShape();

		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouse.setPos(screenX, canvasSize.y - screenY);

		updateShape(screenX, screenY);

		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		if (activeShapeEnum != ActiveShapeEnum.NONE)
			activeShape.changeSize(-amount);

		return true;
	}

	/*
	 * Custom functions
	 */

	public void updateShape(int screenX, int screenY) {
		if (activeShapeEnum != ActiveShapeEnum.NONE)
			activeShape.moveTo(screenX, canvasSize.y - screenY);
	}

	public void addShape() {
		if (activeShapeEnum != ActiveShapeEnum.NONE) {
			Shape shape = activeShape.copy();
			shape.flipY();

			shape.draw(pixmap);
			texture.draw(pixmap, 0, 0);

			drawing.add(shape);
		}
	}

}

package com.feup.asso;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import shapes.Circle;
import utilities.Coord;

public class SimpleDraw extends ApplicationAdapter implements InputProcessor {

	Coord canvasSize;

	ShapeRenderer shapeRenderer;

	ArrayList<Circle> drawing;
	Circle circle;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);

		canvasSize = new Coord(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();

		drawing = new ArrayList<Circle>();
		circle = new Circle();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		for (Circle shape : drawing)
			shape.draw(shapeRenderer);

		circle.draw(shapeRenderer);

		shapeRenderer.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
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
		updateShape(screenX, screenY);

		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		circle.changeSize(-amount);

		return true;
	}

	/*
	 * Custom functions
	 */

	public void updateShape(int screenX, int screenY) {
		circle.moveTo(screenX, canvasSize.y - screenY);
	}

	public void addShape() {
		drawing.add(circle);
		circle = new Circle(circle);
	}

}

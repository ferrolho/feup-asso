package com.feup.asso;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import shapes.Circle;
import shapes.Square;

public class MyGdxGame extends ApplicationAdapter {

	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	Texture img;

	Circle test;
	Square rect;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		test = new Circle();
		rect = new Square();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

		shapeRenderer.begin(ShapeType.Filled);
//		test.draw(shapeRenderer);
		rect.draw(shapeRenderer);
		shapeRenderer.end();
	}

}

package com.magic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MagicSim extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

	}

	@Override
	public void render() {
		ScreenUtils.clear(0.01f, 0.05f, 0.1f, 1);

		// Begin rendering using ShapeRenderer for drawing the circle
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLUE); // Set circle color to blue

		// Draw the circle with center coordinates (x, y) and radius
		float circleX = Gdx.graphics.getWidth() / 2f; // Adjust x-coordinate as needed
		float circleY = Gdx.graphics.getHeight() / 2f; // Adjust y-coordinate as needed
		float radius = 50; // Adjust radius as needed
		shapeRenderer.circle(circleX, circleY, radius);

		shapeRenderer.end(); // End ShapeRenderer drawing
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

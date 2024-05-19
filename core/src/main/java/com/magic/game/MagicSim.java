package com.magic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.magic.game.particle.ParticleFactory;
import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.simulation.Simulation;


public class MagicSim extends ApplicationAdapter {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    Simulation simulation;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        simulation = new Simulation(ParticleFactory.createMany(1),Gdx.graphics.getWidth(), Gdx.graphics.getHeight() ,30, 1);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.01f, 0.05f, 0.1f, 1);

        // Begin rendering using ShapeRenderer for drawing the circle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        simulation.run();
//        shapeRenderer.setColor(BLUE); // Set circle color to blue

        for (MovableSpatialElement element: simulation.getElements()
             ) {
            shapeRenderer.circle(element.getX(), element.getY(), element.getBoundary());
        }

        shapeRenderer.end(); // End ShapeRenderer drawing
    }

    @Override
    public void dispose() {

        batch.dispose();
        shapeRenderer.dispose();

    }
}

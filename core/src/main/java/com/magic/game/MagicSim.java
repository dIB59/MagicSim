package com.magic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.magic.game.particle.ParticleFactory;
import com.magic.game.simulation.Grid;
import com.magic.game.simulation.Simulation;

public class MagicSim extends ApplicationAdapter {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    Simulation simulation;
    OrthographicCamera hudCamera;
    BitmapFont font;


    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        simulation = new Simulation(
            ParticleFactory.createMany(100),
            Gdx.graphics.getDeltaTime(),
            new Grid(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() ,10)
        );
        font = new BitmapFont(Gdx.files.absolute("C:/Users/ibrah/Documents/GitHub/PERSONAL/MagicSim/assets/font.fnt"));
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.position.set(hudCamera.viewportWidth / 2.0f, hudCamera.viewportHeight / 2.0f, 1.0f);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        simulation.run();

        simulation.getElements().stream()
            .peek(element -> shapeRenderer.setColor(element.getColor()))
            .forEach(element -> shapeRenderer.circle(element.getX(), element.getY(), element.getBoundary()));


        shapeRenderer.end();

        hudCamera.update();
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        font.draw(batch, "FPS=" + Gdx.graphics.getFramesPerSecond(), 10, hudCamera.viewportHeight - 10);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();

    }
}

package com.magic.game.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.magic.game.physics.MovableSpatialElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ParticleFactory {
    private static final Random RANDOM = new Random();

    public static List<MovableSpatialElement> createMany(int numberOfParticles) {
        List<MovableSpatialElement> particles = new ArrayList<>();

        for (int i = 0; i < numberOfParticles; i++) {
            particles.add(createOne());
        }
        return particles;
    }

    public static Particle createOne() {
        final ParticleBuilder builder = new ParticleBuilder();

        Color randomColor = getRandomColor();
        int randomMass = getRandomMass();
        int randomRadius = getRandomRadius();
        int randomVelX = getRandomVelocity();
        int randomVelY = getRandomVelocity();
        int randomX = getRandomXPosition();
        int randomY = getRandomYPosition();

        builder.setColor(randomColor)
                .setMass(randomMass)
                .setRadius(randomRadius)
                .setVelX(randomVelX)
                .setVelY(randomVelY)
                .setX(randomX)
                .setY(randomY);

        return builder.createParticle();
    }

    private static Color getRandomColor() {
        float r = RANDOM.nextFloat();
        float g = RANDOM.nextFloat();
        float b = RANDOM.nextFloat();
        return new Color(r, g, b, 1);
    }

    private static int getRandomMass() {
        return RANDOM.nextInt(10) + 1;
    }

    private static int getRandomRadius() {
        return RANDOM.nextInt(16) + 5;
    }

    private static int getRandomVelocity() {
        return RANDOM.nextInt(11) - 5;
    }

    private static int getRandomYPosition() {
        return RANDOM.nextInt( 100, Gdx.graphics.getHeight() - 100);
    }
    private static int getRandomXPosition() {
        return RANDOM.nextInt( 100, Gdx.graphics.getWidth() - 100);
    }

}


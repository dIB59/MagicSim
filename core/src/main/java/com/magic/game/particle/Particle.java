package com.magic.game.particle;

import com.badlogic.gdx.graphics.Color;
import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.ui.Displayable;

import java.util.concurrent.atomic.AtomicInteger;

public final class Particle implements MovableSpatialElement, Displayable {

    private static final AtomicInteger nextId = new AtomicInteger(0);
    private final int id;
    private final Color color;
    private final int mass;
    private final int radius;
    private double velX;
    private double velY;
    private float x;
    private float y;
    private double velocityDampener = 0.9;


    public Particle(Color color, int mass, int radius, int velX, int velY, int x, int y) {
        this.id = nextId.incrementAndGet();
        this.color = color;
        this.mass = mass;
        this.radius = radius;
        this.velX = velX;
        this.velY = velY;
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public void setXCoordinate(float x) {
        this.x = (int) x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setYCoordinate(float y) {
        this.y = (int) y;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getBoundary() {
        return this.radius;
    }

    @Override
    public double getVelocityDampner() {
        return this.velocityDampener;
    }

    @Override
    public void setVelocityDampner(double velocity) {
        this.velocityDampener = velocity;
    }

    @Override
    public double getMass() {
        return this.mass;
    }

    @Override
    public double getVelocityX() {
        return this.velX;
    }

    @Override
    public void setVelocityX(double velocity) {
        this.velX = velocity;
    }

    @Override
    public double getVelocityY() {
        return this.velY;
    }

    @Override
    public void setVelocityY(double velocity) {
        this.velY = velocity;

    }

    public Color getColor() {
        return this.color;
    }

}

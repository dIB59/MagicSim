package com.magic.game.particle;

import com.badlogic.gdx.graphics.Color;
import com.magic.game.physics.MovableSpatialElement;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@ToString
public final class Particle implements MovableSpatialElement {

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
    public void setX(float x) {
        this.x = (int) x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setY(float y) {
        this.y = (int) y;
    }

    @Override
    public int getId() {
        return this.id;
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
    public double getXVel() {
        return this.velX;
    }

    @Override
    public void setXVel(double velocity) {
        this.velX = velocity;
    }

    @Override
    public double getYVel() {
        return this.velY;
    }

    @Override
    public void setYVel(double velocity) {
        this.velY = velocity;

    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

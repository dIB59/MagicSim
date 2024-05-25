package com.magic.game.particle;

import com.badlogic.gdx.graphics.Color;
import com.magic.game.physics.MovableSpatialElement;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@ToString
public final class Particle implements MovableSpatialElement {

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    private final int id;
    private final Color color;
    private final int mass;
    private final int radius;
    private FloatVector position;
    private FloatVector velocity;
    private FloatVector acceleration;
    private float velocityDampener = 0.9F;


    public Particle(Color color, int mass, int radius, int velX, int velY, int x, int y) {
        this.id = NEXT_ID.getAndIncrement();
        this.color = color;
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = FloatVector.zero(SPECIES);

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
    public FloatVector getPosition() {
        return null;
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
    public void update() {

    }

    @Override
    public FloatVector getVelocity() {
        return this.velocity;
    }

    @Override
    public FloatVector getAcceleration() {
        return this.acceleration;
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

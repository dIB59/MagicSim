package com.magic.game.particle;

import com.badlogic.gdx.graphics.Color;
import com.magic.game.physics.Collidable;
import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.physics.SpatialElement;
import com.magic.game.physics.VelocityDampener;
import com.magic.game.ui.Displayable;

import java.util.concurrent.atomic.AtomicInteger;

public final class Particle implements MovableSpatialElement, Collidable<SpatialElement>, VelocityDampener, Displayable {

    private static final AtomicInteger nextId = new AtomicInteger(0);
    private final int id;
    private final Color color;
    private final int mass;
    private final int radius;
    private double velX;
    private double velY;
    private double x;
    private double y;
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
    public double getX() {
        return this.x;
    }

    @Override
    public void setXCoordinate(double x) {
        this.x = (int) x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setYCoordinate(double y) {
        this.y = (int) y;
    }

    @Override
    public void resolveCollision(MovableSpatialElement particle) {
        double dx = particle.getX() - this.getX();
        double dy = particle.getY() - this.getY();
        double squaredDistance = dx * dx + dy * dy;
        int combinedRadius = this.radius + particle.getBoundary();
        int squaredRadius = combinedRadius * combinedRadius;

        if (!(squaredDistance >= squaredRadius)) {
            return;
        }

        double angle = Math.atan2(dy, dx);

        double totalMass = this.mass + particle.getMass();

        this.velX = ((this.mass - particle.getMass()) / totalMass) * this.velX + (2 * particle.getMass() / totalMass)
                * particle.getVelocityX() * this.velocityDampener;
        this.velY = ((this.mass - particle.getMass()) / totalMass) * this.velY + (2 * particle.getMass() / totalMass)
                * particle.getVelocityY() * this.velocityDampener;
        particle.setVelocityX((2 * this.mass / totalMass) * this.velX - ((this.mass - particle.getMass()) / totalMass)
                * particle.getVelocityX() * this.velocityDampener);
        particle.setVelocityY((2 * this.mass / totalMass) * this.velY - ((this.mass - particle.getMass()) / totalMass)
                * particle.getVelocityY() * this.velocityDampener);

        double overlap = combinedRadius - Math.sqrt(squaredDistance);
        double moveX = overlap * Math.cos(angle);
        double moveY = overlap * Math.sin(angle);
        this.x -=  moveX / 2;
        this.y -= moveY / 2;
        particle.setXCoordinate(particle.getVelocityY() + (moveX / 2));
        particle.setYCoordinate(particle.getY() +  (moveY / 2));
    }

    @Override
    public void resolveCollision(SpatialElement element) {
        double dx = element.getX() - this.getX();
        double dy = element.getY() - this.getY();
        double squaredBoundary = dx * dx + dy * dy;
        int combinedBoundary = this.getBoundary() + element.getBoundary();

        if (!(squaredBoundary < (combinedBoundary * combinedBoundary))) {
            return;
        }

        this.velY = -this.velY * this.velocityDampener;
        this.velX = -this.velX * this.velocityDampener;
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
    public void setDampner(double velocity) {
        this.velocityDampener = velocity;
    }

    @Override
    public double getDampner() {
        return this.velocityDampener;
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

package com.magic.game.simulation;

import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.physics.SpatialElement;
import lombok.Getter;

import java.util.List;


public class Simulation implements CollisionHandler {

    private final Grid grid;
    private final float dt;
    @Getter
    private final List<MovableSpatialElement> elements;

    public Simulation(List<MovableSpatialElement> elements, float timeStep, Grid grid) {
        float dt1;

        this.grid = grid;
        this.elements = elements;
        dt1 = timeStep;
        if (dt1 == 0) dt1 += 0.5f;
        this.dt = dt1;

    }

    //TODO: Write a test that shows particles are moving
    public void run() {

        for (MovableSpatialElement element: elements) {
            updatePosition(element);
            if (element.getY() <= 0)  {
                element.setYVel(element.getYVel() * (-0.99));
            }
            if (element.getY() >= grid.getHeight())  {
                element.setYVel(element.getYVel() * (-0.99));
            }
            if (element.getX() >= grid.getWidth())  {
                element.setXVel(element.getXVel() * (-0.99));
            }
            if (element.getX() <= 0)  {
                element.setXVel(element.getXVel() * (-0.99));
            }
            for (MovableSpatialElement element2: elements) {
                if (element.getId() == element2.getId()) continue;
                resolveCollision(element, element2, 1);
            }
        }
    }

    public void removeElement(MovableSpatialElement particle) {
        this.elements.removeIf(p -> p.getId() == particle.getId());
    }

    private void updatePosition(MovableSpatialElement element) {
        float newX = (float) (element.getX() + element.getXVel() * dt);
        float newY = (float) (element.getY() + element.getYVel() * dt);

        element.setX(newX);
        element.setY(newY);
    }


    @Override
    public void resolveCollision(MovableSpatialElement element, MovableSpatialElement particle, float timestep) {
        if (!isColliding(element, particle)) {
            return;
        }
        double deltaX = particle.getX() - element.getX();
        double deltaY = particle.getY() - element.getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double normalX = deltaX / distance;
        double normalY = deltaY / distance;

        double relativeVelX = particle.getXVel() - element.getXVel();
        double relativeVelY = particle.getYVel() - element.getYVel();

        double velocityAlongNormal = relativeVelX * normalX + relativeVelY * normalY;

        if (velocityAlongNormal > 0) {
            return;
        }

        // Calculate restitution coefficient (assuming perfectly elastic collision, e = 1)
        double restitution = 1.0;

        double impulse = -(1 + restitution) * velocityAlongNormal;
        impulse /= (1 / element.getMass() + 1 / particle.getMass());

        // Calculate impulse vector
        double impulseX = impulse * normalX;
        double impulseY = impulse * normalY;

        element.setXVel((float) (element.getXVel() - (1 / element.getMass()) * impulseX));
        element.setYVel((float) (element.getYVel() - (1 / element.getMass()) * impulseY));
        particle.setXVel((float) (particle.getXVel() + (1 / particle.getMass()) * impulseX));
        particle.setYVel((float) (particle.getYVel() + (1 / particle.getMass()) * impulseY));

        element.setX((float) (element.getX() + element.getXVel() * timestep));
        element.setY((float) (element.getY() + element.getYVel() * timestep));
        particle.setX((float) (particle.getX() + particle.getXVel() * timestep));
        particle.setY((float) (particle.getY() + particle.getYVel() * timestep));
    }

    @Override
    public boolean isColliding(SpatialElement element, SpatialElement particle) {
        return Math.abs(element.getX() - particle.getX()) <= element.getBoundary() + particle.getBoundary() &&
            Math.abs(element.getY() - particle.getY()) <= element.getBoundary() + particle.getBoundary();
    }
}

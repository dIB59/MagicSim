package com.magic.game.simulation;


import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.physics.SpatialElement;

public interface CollisionHandler {

    void resolveCollision(MovableSpatialElement element, MovableSpatialElement particle, float timestep);

    default void resolveCollision(SpatialElement element, MovableSpatialElement particle, float timestep) {
        double dx = element.getX() - particle.getX();
        double dy = element.getY() - particle.getY();
        double squaredBoundary = dx * dx + dy * dy;
        int combinedBoundary = particle.getBoundary() + element.getBoundary();

        if (!(squaredBoundary < (combinedBoundary * combinedBoundary))) {
            return;
        }

        particle.setX((float) (-particle.getX() * particle.getVelocityDampner()));
        particle.setY((float) (-particle.getY() * particle.getVelocityDampner()));
    }

    boolean isColliding(SpatialElement element, SpatialElement element2);

}

package com.magic.game.simulation;


import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.physics.SpatialElement;

public interface CollisionHandler {

    default void resolveCollision(MovableSpatialElement element, MovableSpatialElement particle) {
        double dx = particle.getX() - element.getX();
        double dy = particle.getY() - element.getY();
        double squaredDistance = dx * dx + dy * dy;
        int combinedRadius = element.getBoundary() + particle.getBoundary();
        int squaredRadius = combinedRadius * combinedRadius;

        if (!(squaredDistance >= squaredRadius)) {
            return;
        }

        double angle = Math.atan2(dy, dx);

        double totalMass = element.getMass() + particle.getMass();

        element.setVelocityX(((element.getMass() - particle.getMass()) / totalMass) * element.getVelocityX() + (2 * particle.getMass() / totalMass)
                * particle.getVelocityX() * element.getVelocityDampner());
        element.setVelocityY(((element.getMass() - particle.getMass()) / totalMass) * element.getVelocityY() + (2 * particle.getMass() / totalMass)
                * particle.getVelocityY() * element.getVelocityDampner());
        particle.setVelocityX((2 * element.getMass() / totalMass) * element.getVelocityX() - ((element.getMass() - particle.getMass()) / totalMass)
                * particle.getVelocityX() * element.getVelocityDampner());
        particle.setVelocityY((2 * element.getMass() / totalMass) * element.getVelocityY() - ((element.getMass() - particle.getMass()) / totalMass)
                * particle.getVelocityY() * element.getVelocityDampner());

        double overlap = combinedRadius - Math.sqrt(squaredDistance);
        double moveX = overlap * Math.cos(angle);
        double moveY = overlap * Math.sin(angle);
        element.setXCoordinate((float) (element.getX() - (moveX / 2)));
        element.setYCoordinate((float) (element.getY() - (moveY / 2)));
        particle.setXCoordinate((float) (particle.getX() + (moveX / 2)));
        particle.setYCoordinate((float) (particle.getY() + (moveY / 2)));
    }

    default void resolveCollision(SpatialElement element, MovableSpatialElement particle) {
        double dx = element.getX() - particle.getX();
        double dy = element.getY() - particle.getY();
        double squaredBoundary = dx * dx + dy * dy;
        int combinedBoundary = particle.getBoundary() + element.getBoundary();

        if (!(squaredBoundary < (combinedBoundary * combinedBoundary))) {
            return;
        }

        particle.setXCoordinate((float) (-particle.getX() * particle.getVelocityDampner()));
        particle.setYCoordinate((float) (-particle.getY() * particle.getVelocityDampner()));
    }

    boolean isColliding(SpatialElement element, SpatialElement element2);

}

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
//                System.out.println("Collision Y down");
                element.setYVel(element.getYVel() * (-0.99));
            }
            if (element.getY() >= grid.getHeight())  {
//                System.out.println("Collision Y up");
                element.setYVel(element.getYVel() * (-0.99));
            }
            if (element.getX() >= grid.getWidth())  {
//                System.out.println("Collision X right");
                element.setXVel(element.getXVel() * (-0.99));
            }
            if (element.getX() <= 0)  {
//                System.out.println("Collision X left");
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
        double theta = Math.atan2(particle.getY() - element.getY(), particle.getX() - element.getX());
        double thetaReflection = Math.PI - theta;

        double tempXVel = element.getXVel();
        double tempYVel = element.getYVel();
        element.setXVel(Math.cos(thetaReflection) * tempXVel + Math.sin(thetaReflection) * particle.getXVel());
        element.setYVel(Math.sin(thetaReflection) * tempXVel + Math.cos(thetaReflection) * particle.getYVel());
        particle.setXVel(Math.cos(thetaReflection) * particle.getXVel() + Math.sin(thetaReflection) * tempXVel);
        particle.setYVel(Math.sin(thetaReflection) * particle.getXVel() + Math.cos(thetaReflection) * tempYVel);

        element.setX((float) (element.getX() + element.getXVel()) * timestep);
        element.setY((float) (element.getY() + element.getYVel()) * timestep);
        particle.setX((float) (particle.getX() + particle.getXVel()) * timestep);
        particle.setY((float) (particle.getY() + particle.getYVel()) * timestep);
    }

    @Override
    public boolean isColliding(SpatialElement element, SpatialElement particle) {
        return Math.abs(element.getX() - particle.getX()) <= element.getBoundary() + particle.getBoundary() &&
            Math.abs(element.getY() - particle.getY()) <= element.getBoundary() + particle.getBoundary();
    }
}

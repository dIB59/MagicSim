package com.magic.game.physics;

public interface SpatialElement {

    void resolveCollision(SpatialElement spatialElement);

    int getId();

    double getX();

    void setXCoordinate(double xCoordinate);

    double getY();

    void setYCoordinate(double yCoordinate);

    int getBoundary();

}

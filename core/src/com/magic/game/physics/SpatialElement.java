package com.magic.game.physics;

public interface SpatialElement {

    int getId();

    float getX();

    void setXCoordinate(float xCoordinate);

    float getY();

    void setYCoordinate(float yCoordinate);

    int getBoundary();

}

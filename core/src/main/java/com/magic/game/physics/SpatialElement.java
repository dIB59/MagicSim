package com.magic.game.physics;

import com.badlogic.gdx.graphics.Color;

public interface SpatialElement {

    Color getColor();

    int getId();

    float getX();

    void setXCoordinate(float xCoordinate);

    float getY();

    void setYCoordinate(float yCoordinate);

    int getBoundary();

}

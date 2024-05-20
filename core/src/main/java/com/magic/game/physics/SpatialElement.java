package com.magic.game.physics;

import com.badlogic.gdx.graphics.Color;

public interface SpatialElement {

    Color getColor();

    int getId();

    float getX();

    void setX(float xCoordinate);

    float getY();

    void setY(float yCoordinate);

    int getBoundary();

}

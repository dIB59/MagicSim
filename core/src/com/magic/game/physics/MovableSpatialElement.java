package com.magic.game.physics;

public interface MovableSpatialElement extends SpatialElement{

    void resolveCollision(MovableSpatialElement movableSpatialElement);

    double getMass();
    double getVelocityX();
    void setVelocityX(double velocity);
    double getVelocityY();
    void setVelocityY(double velocity);

}

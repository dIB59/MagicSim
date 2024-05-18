package com.magic.game.physics;

public interface MovableSpatialElement extends SpatialElement {

    double getVelocityDampner();
    void setVelocityDampner(double friction);
    double getMass();
    double getVelocityX();
    void setVelocityX(double velocity);
    double getVelocityY();
    void setVelocityY(double velocity);

}

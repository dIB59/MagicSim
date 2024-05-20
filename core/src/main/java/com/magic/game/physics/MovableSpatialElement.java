package com.magic.game.physics;

public interface MovableSpatialElement extends SpatialElement {

    double getVelocityDampner();
    void setVelocityDampner(double friction);
    double getMass();
    double getXVel();
    void setXVel(double velocity);
    double getYVel();
    void setYVel(double velocity);

}

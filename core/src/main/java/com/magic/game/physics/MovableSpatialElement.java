package com.magic.game.physics;

import jdk.incubator.vector.FloatVector;

public interface MovableSpatialElement extends SpatialElement {

    float getVelocityDampner();
    void setVelocityDampner(float friction);
    double getMass();
    void update();
    FloatVector getVelocity();
    FloatVector getAcceleration();


}

package com.magic.game.physics;


public interface Collidable<T extends SpatialElement> {

    void resolveCollision(T element);

}
package com.magic.game.physics;

import com.badlogic.gdx.graphics.Color;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.concurrent.atomic.AtomicInteger;

public interface SpatialElement {
    AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    VectorSpecies<Float> SPECIES = FloatVector.SPECIES_64;

    Color getColor();

    int getId();

    FloatVector getPosition();

    int getBoundary();

}

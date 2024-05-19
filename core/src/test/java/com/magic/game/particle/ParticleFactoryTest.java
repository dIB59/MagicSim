package com.magic.game.particle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.magic.game.physics.MovableSpatialElement;

import java.util.List;

import org.junit.jupiter.api.Test;

class ParticleFactoryTest {
    /**
     * Method under test: {@link ParticleFactory#createMany(int)}
     */
    @Test
    void testCreateMany() {

        // Arrange and Act
        var created = ParticleFactory.createMany(10);

        assertEquals(10, created.size());
    }

    /**
     * Method under test: {@link ParticleFactory#createMany(int)}
     */
    @Test
    void testCreateMany2() {
        // Arrange and Act
        List<MovableSpatialElement> actualCreateManyResult = ParticleFactory.createMany(0);

        // Assert
        assertTrue(actualCreateManyResult.isEmpty());
    }
}

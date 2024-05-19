package com.magic.game.particle;

import com.magic.game.physics.MovableSpatialElement;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ParticleFactoryTest {
    /**
     * Method under test: {@link ParticleFactory#createMany(int)}
     */
    @Test
    public void testDistinctIds() {
        int numParticles = 10;

        List<MovableSpatialElement> createdParticles = ParticleFactory.createMany(numParticles);

        assertEquals(numParticles, createdParticles.size(), "Number of created particles is incorrect");

        Set<Integer> seenIds = new HashSet<>(10);

        for (MovableSpatialElement particle : createdParticles) {
            int id = particle.getId();

            assertFalse(seenIds.contains(id), "Duplicate ID found: " + id);
            seenIds.add(id);
        }
    }

    /**
     * Method under test: {@link ParticleFactory#createMany(int)}
     */
    @Test
    void testCreateZero() {
        // Arrange and Act
        List<MovableSpatialElement> actualCreateManyResult = ParticleFactory.createMany(0);

        // Assert
        assertTrue(actualCreateManyResult.isEmpty());
    }

    /**
     * Method under test: {@link ParticleFactory#createMany(int)}
     */
    @Test
    public void testCreateMany() {
        int numParticles = 20;

        List<MovableSpatialElement> createdParticles = ParticleFactory.createMany(numParticles);

        assertEquals(numParticles, createdParticles.size(), "Number of created particles is incorrect");
    }

    @Test
    void testCreateOne() {

        Particle actualCreateOneResult = ParticleFactory.createOne();
        assertEquals(0.9d, actualCreateOneResult.getVelocityDampner());
    }
}

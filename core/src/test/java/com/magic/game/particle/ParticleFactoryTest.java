package com.magic.game.particle;

import com.magic.game.physics.MovableSpatialElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ParticleFactoryTest {
    /**
     * Method under test: {@link ParticleFactory#createMany(int)}
     */
    @Test
    void testCreateMany() {
        // Arrange
        // TODO: Populate arranged inputs
        int numberOfParticles = 10;

        // Act
        List<MovableSpatialElement> actualCreateManyResult = ParticleFactory.createMany(numberOfParticles);

        // Assert
        // TODO: Add assertions on result
        Assertions.assertEquals(numberOfParticles, actualCreateManyResult.size());
    }
}

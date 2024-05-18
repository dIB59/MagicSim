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
        int numberOfParticles = 10;

        List<MovableSpatialElement> actualCreateManyResult = ParticleFactory.createMany(numberOfParticles);

        Assertions.assertEquals(numberOfParticles, actualCreateManyResult.size());
    }
}

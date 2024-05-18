package com.magic.game.particle;

import com.badlogic.gdx.graphics.Color;

public class ParticleBuilder {
    private Color color;
    private int mass;
    private int radius;
    private int velX;
    private int velY;
    private int x;
    private int y;

    public ParticleBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public ParticleBuilder setMass(int mass) {
        this.mass = mass;
        return this;
    }

    public ParticleBuilder setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public ParticleBuilder setVelX(int velX) {
        this.velX = velX;
        return this;
    }

    public ParticleBuilder setVelY(int velY) {
        this.velY = velY;
        return this;
    }

    public ParticleBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public ParticleBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public Particle createParticle() {
        return new Particle(color, mass, radius, velX, velY, x, y);
    }
}
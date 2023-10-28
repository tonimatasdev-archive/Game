package dev.tonimatas.game.entity;

import dev.tonimatas.game.util.Location;

public class Entity {
    public Location location;
    public int speed;

    public Entity(Location location) {
        this.location = location;
        this.speed = 1;
    }
}

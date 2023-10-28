package dev.tonimatas.game.network.packet;

import dev.tonimatas.game.entity.Player;

public interface PlayerMovePacket {
    void handle(Player player);

    default Player readPacket(byte[] bytes) {

        return null;
    }

    default byte[] writePacket(Player player) {

        return null;
    }
}

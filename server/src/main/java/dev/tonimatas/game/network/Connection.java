package dev.tonimatas.game.network;

import dev.tonimatas.game.network.packet.PlayerMovePacket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Connection {
    protected Socket socket;
    protected Thread thread;

    public Connection(Socket socket) {
        this.socket = socket;
        this.thread = new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();

                byte[] bytes = inputStream.readAllBytes();

                int id = (bytes[0] << 24) | (bytes[1] << 16) | (bytes[2] << 8) | bytes[3];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Socket getSocket() {
        return socket;
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }
}

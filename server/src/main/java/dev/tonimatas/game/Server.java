package dev.tonimatas.game;

import dev.tonimatas.game.network.ConnectionManager;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
    public static ServerSocket serverSocket;
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (serverSocket == null) System.exit(0);

        ConnectionManager.init(serverSocket);

        new Server().run();
    }

    private static void tick() {
    }

    @Override
    public void run() {
        double tickInterval = 1000000000d / 20;
        double nextTickTime = System.nanoTime() + tickInterval;

        while (true) {
            tick();

            try {
                double reamingTime = nextTickTime - System.nanoTime();
                reamingTime = reamingTime / 1000000;

                if (reamingTime < 0) {
                    reamingTime = 0;
                }

                Thread.sleep((long) reamingTime);

                nextTickTime += tickInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

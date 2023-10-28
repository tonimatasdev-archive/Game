package dev.tonimatas.game.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
    private static final List<Connection> connections = new ArrayList<>();

    public static void init(ServerSocket serverSocket) {
       new Thread("ConnectionManager") {
            @Override
            public void run() {
                Socket socket;

                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Player connected:" + socket.getInetAddress());

                connections.add(new Connection(socket));
            }
        }.start();

    }
}

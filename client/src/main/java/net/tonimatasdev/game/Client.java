package net.tonimatasdev.game;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("Game");

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        windows.pack();

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        Socket socket;
        try {
            socket = new Socket("0.0.0.0", 9999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gamePanel.startGameThread();
    }
}

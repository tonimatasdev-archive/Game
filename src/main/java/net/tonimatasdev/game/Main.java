package net.tonimatasdev.game;

import javax.swing.*;

public class Main {
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

        TickManager tick = new TickManager();
        tick.startTickThread();
        gamePanel.startGameThread();
    }
}
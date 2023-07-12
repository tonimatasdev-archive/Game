package net.tonimatasdev;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final  int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000d / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double reamingTime = nextDrawTime - System.nanoTime();
                reamingTime = reamingTime / 1000000;

                if (reamingTime < 0) {
                    reamingTime = 0;
                }

                Thread.sleep((long) reamingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
        graphics2D.dispose();
    }
}

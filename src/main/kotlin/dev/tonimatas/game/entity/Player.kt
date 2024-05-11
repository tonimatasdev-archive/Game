package dev.tonimatas.game.entity

import dev.tonimatas.game.GamePanel
import dev.tonimatas.game.KeyHandler
import java.awt.Color
import java.awt.Graphics2D

class Player(var gamePanel: GamePanel, var keyHandler: KeyHandler) : Entity() {
    init {
        x = 100
        y = 100
        speed = 4
    }

    fun update() {
        if (keyHandler.upPressed) {
            y -= speed
        } else if (keyHandler.downPressed) {
            y += speed
        } else if (keyHandler.leftPressed) {
            x -= speed
        } else if (keyHandler.rightPressed) {
            x += speed
        }
    }
    
    fun draw(graphics2D: Graphics2D) {
        graphics2D.color = Color.WHITE
        graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize)
    }
}
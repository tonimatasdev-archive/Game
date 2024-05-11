package dev.tonimatas.game.entity

import dev.tonimatas.game.GamePanel
import dev.tonimatas.game.KeyHandler
import java.awt.Color
import java.awt.Graphics2D

class Player(var gamePanel: GamePanel, var keyHandler: KeyHandler) : Entity() {
    var screenX: Int = 0
    var screenY: Int = 0
    
    
    init {
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2)
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2)
        worldX = gamePanel.tileSize * 23
        worldY = gamePanel.tileSize * 21
        speed = 4
    }

    fun update() {
        
        if (keyHandler.upPressed) {
            worldY -= speed
        } else if (keyHandler.downPressed) {
            worldY += speed
        } else if (keyHandler.leftPressed) {
            worldX -= speed
        } else if (keyHandler.rightPressed) {
            worldX += speed
        }
    }
    
    fun draw(graphics2D: Graphics2D) {
        graphics2D.color = Color.WHITE
        graphics2D.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)
    }
}
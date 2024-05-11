package dev.tonimatas.game.entity

import dev.tonimatas.game.GamePanel
import dev.tonimatas.game.KeyHandler
import java.awt.Color
import java.awt.Graphics2D

class Player(private var gamePanel: GamePanel, private var keyHandler: KeyHandler) : Entity() {
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
        var speedX = 0
        var speedY = 0
        
        if (keyHandler.upPressed) speedY -= speed
        if (keyHandler.downPressed) speedY += speed
        if (keyHandler.leftPressed) speedX -= speed
        if (keyHandler.rightPressed) speedX += speed
        
        if (speedX != 0 && speedY != 0) {
            if (keyHandler.upPressed && keyHandler.leftPressed) {
                worldX += speedX+1
                worldY += speedY+1
            }

            if (keyHandler.upPressed && keyHandler.rightPressed) {
                worldX += speedX-1
                worldY += speedY+1
            }

            if (keyHandler.downPressed && keyHandler.leftPressed) {
                worldX += speedX+1
                worldY += speedY-1
            }

            if (keyHandler.downPressed && keyHandler.rightPressed) {
                worldX += speedX-1
                worldY += speedY-1
            }
        } else {
            worldX += speedX
            worldY += speedY
        }
    }
    
    fun draw(graphics2D: Graphics2D) {
        graphics2D.color = Color.WHITE
        graphics2D.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)
    }
}
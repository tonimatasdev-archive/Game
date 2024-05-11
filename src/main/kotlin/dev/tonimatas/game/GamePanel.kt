package dev.tonimatas.game

import dev.tonimatas.game.entity.Player
import dev.tonimatas.game.tile.TileManager
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class GamePanel() : JPanel(), Runnable {
    // SCREEN SETTINGS
    val originalTileSize: Int = 16 // 16x16 tile
    val scale: Int = 3

    val tileSize: Int = originalTileSize * scale // 48x48 tile
    val maxScreenColumn: Int = 16
    val maxScreenRow: Int = 12
    val screenWidth: Int = tileSize * maxScreenColumn
    val screenHeight: Int = tileSize * maxScreenRow

    // FPS
    var FPS: Int = 60
    
    val tileManager = TileManager(this)
    val keyHandler = KeyHandler()
    var gameThread: Thread? = null
    val player = Player(this, this.keyHandler)

    init {
        this.preferredSize = Dimension(screenWidth, screenHeight)
        this.background = Color.BLACK
        this.isDoubleBuffered = true
        this.addKeyListener(keyHandler)
        this.isFocusable = true
    }


    fun startGameThread() {
        gameThread = Thread(this)
        gameThread!!.start()
    }

    override fun run() {
        val drawInterval = 1000000000.0 / FPS
        var nextDrawTime = System.nanoTime() + drawInterval

        while (gameThread != null) {
            update()
            repaint()
            
            try {
                var reamingTime = nextDrawTime - System.nanoTime()
                reamingTime = reamingTime / 1000000

                if (reamingTime < 0) {
                    reamingTime = 0.0
                }

                Thread.sleep(reamingTime.toLong())

                nextDrawTime += drawInterval
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
    }

    private fun update() {
        player.update()
    }

    public override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)

        val graphics2D = graphics as Graphics2D

        tileManager.draw(graphics2D)
        
        player.draw(graphics2D)
    }
}
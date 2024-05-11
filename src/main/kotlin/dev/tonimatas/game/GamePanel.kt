package dev.tonimatas.game

import dev.tonimatas.game.entity.Player
import dev.tonimatas.game.tile.TileManager
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class GamePanel : JPanel(), Runnable {
    // SCREEN SETTINGS
    private val originalTileSize: Int = 16 // 16x16 tile
    private val scale: Int = 3

    val tileSize: Int = originalTileSize * scale // 48x48 tile
    private val maxScreenColumn: Int = 16
    private val maxScreenRow: Int = 12
    var screenWidth: Int = tileSize * maxScreenColumn
    var screenHeight: Int = tileSize * maxScreenRow

    // WORLD SETTINGS
    val maxWorldColumn = 50
    val maxWorldRow = 50
    //val worldWidth = maxWorldColumn * tileSize
    //val worldHeight = maxWorldRow * tileSize
    
    // FPS
    private var fps: Int = 60
    
    private val tileManager = TileManager(this)
    private val keyHandler = KeyHandler()
    private var gameThread: Thread? = null
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
        val drawInterval = 1000000000.0 / fps
        var nextDrawTime = System.nanoTime() + drawInterval

        while (gameThread != null) {
            update()
            repaint()
            
            try {
                var reamingTime = nextDrawTime - System.nanoTime()
                reamingTime /= 1000000

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
package dev.tonimatas.game.tile

import dev.tonimatas.game.GamePanel
import java.awt.Graphics2D
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.imageio.ImageIO

class TileManager(var gamePanel: GamePanel) {
    var tile: Array<Tile?>
    var mapTileNumber: Array<Array<Int?>>

    init {
        tile = arrayOfNulls(10)
        mapTileNumber = Array(gamePanel.maxScreenColumn) {arrayOfNulls(gamePanel.maxScreenRow)}

        getTileImage()
        loadMap()
    }
    
    fun getTileImage() {
        tile[0] = Tile()
        tile[0]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/grass.png"))

        tile[1] = Tile()
        tile[1]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/wall.png"))

        tile[2] = Tile()
        tile[2]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/water.png"))
    }
    
    fun loadMap() {
        val inputStream = javaClass.getResourceAsStream("/maps/map01.txt")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream!!))

        var column = 0
        var row = 0

        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
            val line = bufferedReader.readLine()
            
            while (column < gamePanel.maxScreenColumn) {
                val numbers = line.split(" ")
                
                val number = Integer.parseInt(numbers[column])
                
                mapTileNumber[column][row] = number
                column++
            }
            
            if (column == gamePanel.maxScreenColumn) {
                column = 0
                row++
            }
        }
        
        bufferedReader.close()
    }
    
    fun draw(graphics2D: Graphics2D) {
        
        var column = 0
        var row = 0
        var x = 0
        var y = 0
        
        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
            
            val tileNumber = mapTileNumber[column][row]
            
            graphics2D.drawImage(tile[tileNumber!!]!!.image, x, y, gamePanel.tileSize, gamePanel.tileSize, null)
            column++
            x += gamePanel.tileSize
            
            if (column == gamePanel.maxScreenColumn) {
                column = 0
                x = 0
                row++
                y += gamePanel.tileSize
            }
        }
    }
}
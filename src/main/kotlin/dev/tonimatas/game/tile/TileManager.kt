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
        mapTileNumber = Array(gamePanel.maxWorldColumn) {arrayOfNulls(gamePanel.maxWorldRow)}

        getTileImage()
        loadMap("world01")
    }
    
    fun getTileImage() {
        tile[0] = Tile()
        tile[0]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/grass.png"))

        tile[1] = Tile()
        tile[1]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/wall.png"))

        tile[2] = Tile()
        tile[2]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/water.png"))

        tile[3] = Tile()
        tile[3]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/earth.png"))

        tile[4] = Tile()
        tile[4]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/tree.png"))

        tile[5] = Tile()
        tile[5]!!.image = ImageIO.read(javaClass.getResourceAsStream("/tiles/sand.png"))
    }
    
    fun loadMap(worldName: String) {
        val inputStream = javaClass.getResourceAsStream("/worlds/$worldName.txt")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream!!))

        var column = 0
        var row = 0

        while (column < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {
            val line = bufferedReader.readLine()
            
            while (column < gamePanel.maxWorldColumn) {
                val numbers = line.split(" ")
                
                val number = Integer.parseInt(numbers[column])
                
                mapTileNumber[column][row] = number
                column++
            }
            
            if (column == gamePanel.maxWorldColumn) {
                column = 0
                row++
            }
        }
        
        bufferedReader.close()
    }
    
    fun draw(graphics2D: Graphics2D) {
        
        var worldColumn = 0
        var worldRow = 0
        
        while (worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow) {
            
            val tileNumber = mapTileNumber[worldColumn][worldRow]
            
            val worldX = worldColumn * gamePanel.tileSize
            val worldY = worldRow * gamePanel.tileSize
            val screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX
            val screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY
            
            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                graphics2D.drawImage(tile[tileNumber!!]!!.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null)
            }
            
            worldColumn++
            
            if (worldColumn == gamePanel.maxWorldColumn) {
                worldColumn = 0
                worldRow++
            }
        }
    }
}
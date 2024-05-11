package dev.tonimatas.game

import javax.swing.JFrame

class Core {
    init {
        val window = JFrame()
        window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        window.isResizable = true
        window.title = "Game"

        val gamePanel = GamePanel()
        window.add(gamePanel)

        window.pack()

        window.setLocationRelativeTo(null)
        window.isVisible = true

        gamePanel.startGameThread()
    }
}
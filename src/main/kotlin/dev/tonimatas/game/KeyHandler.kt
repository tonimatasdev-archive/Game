package dev.tonimatas.game

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyHandler : KeyListener {
    var upPressed: Boolean = false
    var downPressed: Boolean = false
    var leftPressed: Boolean = false
    var rightPressed: Boolean = false

    override fun keyTyped(e: KeyEvent) {
    }

    override fun keyPressed(e: KeyEvent) {
        val code = e.keyCode

        if (code == KeyEvent.VK_W) {
            upPressed = true
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true
        }
    }

    override fun keyReleased(e: KeyEvent) {
        val code = e.keyCode

        if (code == KeyEvent.VK_W) {
            upPressed = false
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false
        }
    }
}
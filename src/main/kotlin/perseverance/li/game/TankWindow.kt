package perseverance.li.game

import javafx.application.Application
import javafx.scene.input.KeyEvent
import perseverance.li.engine.core.Window
import perseverance.li.game.model.*
import java.io.File

class TankWindow : Window(title = "坦克大战1.0",
        icon = "icon/logo.png",
        width = Config.gameWidth,
        height = Config.gameHeight) {

    var mapViewList = arrayListOf<View>()

    override fun onCreate() {
        val mapFile = File(javaClass.getResource("/map/1.map").path)
        val lines: List<String> = mapFile.readLines()
        var row = 0
        lines.forEach {
            var column = 0
            it.toCharArray().forEach {
                val x = column * Config.block
                val y = row * Config.block
                when (it) {
                    '砖' -> mapViewList.add(Wall(x, y))
                    '草' -> mapViewList.add(Grass(x, y))
                    '铁' -> mapViewList.add(Steel(x, y))
                    '水' -> mapViewList.add(Water(x, y))
                }
                column++
            }
            row++
        }
    }

    override fun onDisplay() {
        mapViewList.forEach {
            it.draw()
        }
    }

    override fun onRefresh() {
    }

    override fun onKeyPressed(event: KeyEvent) {
    }
}

fun main(args: Array<String>) {
    Application.launch(TankWindow::class.java)
}
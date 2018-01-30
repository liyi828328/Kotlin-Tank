package perseverance.li.game

import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import perseverance.li.engine.core.Window
import perseverance.li.game.business.IBlockable
import perseverance.li.game.business.IMovable
import perseverance.li.game.model.*
import perseverance.li.game.enums.Direction
import java.io.File

class TankWindow : Window(title = "坦克大战1.0",
        icon = "icon/logo.png",
        width = Config.gameWidth,
        height = Config.gameHeight) {

    private lateinit var tank: Tank
    private var mapViewList = arrayListOf<View>()

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
        tank = Tank(2 * Config.block, 12 * Config.block)
        mapViewList.add(tank)
    }

    override fun onDisplay() {
        mapViewList.forEach {
            it.draw()
        }
    }

    override fun onRefresh() {
        //检测移动物体的碰撞
        //1.找到移动物体
        //2.找到阻塞物体
        //3.检测移动物体是否可以与周围的阻塞物体产生碰撞
        mapViewList.filter { it is IMovable }.forEach { move ->
            move as IMovable
            var badDirection: Direction? = null
            var badBlockView: View? = null
            mapViewList.filter { it is IBlockable }.forEach breakTag@ { block ->
                block as IBlockable
                //检测运动物体与阻塞物体是否可以发生碰撞
                val direction = move.willCollision(block)
                if (direction != null) {
                    badBlockView = block
                    badDirection = direction
                    return@breakTag
                }
            }
            move.notifyCollision(badDirection, badBlockView)
        }

    }

    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.UP, KeyCode.W -> {
                tank.move(Direction.UP)
            }
            KeyCode.DOWN, KeyCode.S -> {
                tank.move(Direction.DOWN)
            }
            KeyCode.LEFT, KeyCode.A -> {
                tank.move(Direction.LEFT)
            }
            KeyCode.RIGHT, KeyCode.D -> {
                tank.move(Direction.RIGHT)
            }
            KeyCode.SPACE, KeyCode.BACK_SPACE -> {
                println("shoot")
                mapViewList.add(tank.shoot())
            }
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(TankWindow::class.java)
}
package perseverance.li.game.model

import perseverance.li.engine.core.Painter
import perseverance.li.game.Config
import perseverance.li.game.business.IAutoMovable
import perseverance.li.game.business.IDestroyable
import perseverance.li.game.enums.Direction

/**
 * 绘制子弹
 *
 * 这里构造里传入了函数，kotlin特性
 */
class Bullet(override val currentDirection: Direction,
             calculateCoordinate: (bulletImgW: Int, bulletImgH: Int) -> Pair<Int, Int>) : View(), IAutoMovable, IDestroyable {

    override val speed: Int = 8
    override var x: Int = 0
    override var y: Int = 0
    private var bulletWidth = 0
    private var bulletHeight: Int = 0

    private val bulletImg = when (currentDirection) {
        Direction.UP -> "img/bullet_u.gif"
        Direction.DOWN -> "img/bullet_d.gif"
        Direction.LEFT -> "img/bullet_l.gif"
        Direction.RIGHT -> "img/bullet_r.gif"
    }

    init {
        val size = Painter.size(bulletImg)
        bulletWidth = size[0]
        bulletHeight = size[1]
        val corrdinate = calculateCoordinate.invoke(bulletWidth, bulletHeight)
        x = corrdinate.first
        y = corrdinate.second
    }

    override fun getViewImg(): String {
        return bulletImg
    }

    override fun autoMove() {
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        super.draw()
    }

    override fun isDestory(): Boolean =
            x < -bulletWidth || x > Config.gameWidth || y < -bulletHeight || y > Config.gameHeight
}
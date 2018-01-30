package perseverance.li.game.model

import perseverance.li.engine.core.Painter
import perseverance.li.game.enums.Direction

/**
 * 绘制子弹
 *
 * 这里构造里传入了函数，kotlin特性
 */
class Bullet(direction: Direction, calculateCoordinate: (bulletImgW: Int, bulletImgH: Int) -> Pair<Int, Int>) : View() {

    override var x: Int = 0
    override var y: Int = 0
    private val bulletImg = when (direction) {
        Direction.UP -> "img/bullet_u.gif"
        Direction.DOWN -> "img/bullet_d.gif"
        Direction.LEFT -> "img/bullet_l.gif"
        Direction.RIGHT -> "img/bullet_r.gif"
    }

    init {
        val size = Painter.size(bulletImg)
        val bulletImgW = size[0]
        val bulletImgH = size[1]
        val corrdinate = calculateCoordinate.invoke(bulletImgW, bulletImgH)
        x = corrdinate.first
        y = corrdinate.second
    }

    override fun getViewImg(): String {
        return bulletImg
    }
}
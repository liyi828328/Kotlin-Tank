package perseverance.li.game.model

import perseverance.li.engine.core.Painter
import perseverance.li.game.Config
import perseverance.li.game.business.IMovable
import perseverance.li.game.enums.Direction

/**
 * 继承后，可将val 修改为 var
 */
class Tank(override var x: Int, override var y: Int) : View(), IAction, IMovable {

    private var img: String = "img/tank_u.gif"
    /**
     * 坦克速度
     */
    private val speed: Int = 16
    /**
     * 当前方向
     */
    private var currentDirection: Direction = Direction.UP
    /**
     * 不可以走的方向
     */
    private var badDirection: Direction? = null
    /**
     * 坦克宽度
     */
    private var width: Int = 0
    /**
     * 坦克高度
     */
    private var height: Int = 0

    init {
        currentDirection = Direction.UP
        var size = size()
        width = size[0]
        height = size[1]
    }

    /**
     * 计算当前坦克大小
     */
    fun size(): Array<Int> {
        return Painter.size(getImg(currentDirection))
    }

    /**
     * 获取当前坦克
     */
    fun getImg(direction: Direction): String {
        return when (direction) {
            Direction.UP -> "img/tank_u.gif"
            Direction.DOWN -> "img/tank_d.gif"
            Direction.LEFT -> "img/tank_l.gif"
            Direction.RIGHT -> "img/tank_r.gif"
        }
    }

    /**
     * 坦克移动
     */
    override fun move(direction: Direction) {
        img = getImg(direction)
        if (currentDirection != direction) {
            currentDirection = direction
            super.draw()
            return
        }
        //检测碰撞的发生，如果碰撞了则不移动
        if (currentDirection == badDirection) return

        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        if (x < 0) x = 0
        if (x > Config.gameWidth - width) x = Config.gameWidth - width
        if (y < 0) y = 0
        if (y > Config.gameHeight - height) y = Config.gameHeight - height
        currentDirection = direction
        super.draw()
    }

    override fun getViewImg(): String {
        return img
    }

    override fun willCollision(blockView: View): Direction? {
        var x: Int = this.x
        var y: Int = this.y
        //当前方向的下一个位置
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        val collision: Boolean = when {
            x + width <= blockView.x -> false //阻塞物在右侧
            blockView.x + blockView.getViewWidth() <= x -> false//阻塞物在左侧
            y + height <= blockView.y -> false//阻塞物在下侧
            blockView.y + blockView.getViewHeight() <= y -> false//阻塞物在下侧
            else -> true
        }
        return if (collision) currentDirection else null
    }

    override fun notifyCollision(direction: Direction?, blockView: View?) {
        badDirection = direction
    }

    /**
     * 发射子弹
     */
    fun shoot(): Bullet {
        return Bullet(currentDirection) { bulletWidth, bulletHeight ->
            var bulletX = 0
            var bulletY = 0
            when (currentDirection) {
                Direction.UP -> {
                    bulletX = x + (width - bulletWidth) / 2
                    bulletY = y - bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = x + (width - bulletWidth) / 2
                    bulletY = y + (height - bulletHeight / 2)
                }
                Direction.LEFT -> {
                    bulletX = x - bulletWidth / 2
                    bulletY = y + (height - bulletHeight) / 2
                }
                Direction.RIGHT -> {
                    bulletX = x + (width - bulletWidth / 2)
                    bulletY = y + (height - bulletHeight) / 2
                }
            }
            Pair(bulletX, bulletY)
        }
    }
}
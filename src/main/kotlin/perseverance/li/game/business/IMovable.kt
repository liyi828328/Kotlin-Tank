package perseverance.li.game.business

import perseverance.li.game.enums.Direction
import perseverance.li.game.model.View

/**
 * 具有移动功能的模型
 */
interface IMovable {
    /**
     * 检测是否可以发生碰撞
     */
    fun willCollision(blockView: View): Direction?

    /**
     * 通知发生碰撞
     *
     * 如果没有碰装，参数为null
     */
    fun notifyCollision(direction: Direction?, blockView: View?)
}
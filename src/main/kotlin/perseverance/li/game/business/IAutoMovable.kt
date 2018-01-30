package perseverance.li.game.business

import perseverance.li.game.enums.Direction

/**
 * 具有自动移动的模型
 */
interface IAutoMovable {
    /**
     * 方向
     */
    val currentDirection: Direction
    /**
     * 速度
     */
    val speed: Int

    fun autoMove()
}
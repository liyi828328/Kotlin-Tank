package perseverance.li.game.model

import perseverance.li.game.business.IBlockable

/**
 * 墙体View
 */
class Wall(override val x: Int, override val y: Int) : View(), IBlockable {
    override fun getViewImg(): String {
        return "img/wall.gif"
    }
}
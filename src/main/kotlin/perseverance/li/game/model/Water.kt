package perseverance.li.game.model

import perseverance.li.game.business.IBlockable

/**
 * 水 View
 */
class Water(override val x: Int, override val y: Int) : View(), IBlockable {
    override fun getViewImg(): String {
        return "img/water.gif"
    }
}
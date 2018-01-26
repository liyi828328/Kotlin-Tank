package perseverance.li.game.model

import perseverance.li.game.business.IBlockable

/**
 * 铁墙View
 */
class Steel(override val x: Int, override val y: Int) : View(), IBlockable {
    override fun getViewImg(): String {
        return "img/steel.gif"
    }
}
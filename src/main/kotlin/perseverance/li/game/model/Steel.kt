package perseverance.li.game.model

/**
 * 铁墙View
 */
class Steel(override val x: Int, override val y: Int) : View() {
    override fun getViewImg(): String {
        return "img/steel.gif"
    }
}
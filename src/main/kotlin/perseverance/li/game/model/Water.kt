package perseverance.li.game.model

/**
 * 水 View
 */
class Water(override val x: Int, override val y: Int) : View() {
    override fun getViewImg(): String {
        return "img/water.gif"
    }
}
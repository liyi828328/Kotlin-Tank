package perseverance.li.game.model

/**
 * 墙体View
 */
class Wall(override val x: Int, override val y: Int) : View() {
    override fun getViewImg(): String {
        return "img/wall.gif"
    }
}
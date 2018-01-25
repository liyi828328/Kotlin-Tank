package perseverance.li.game.model

/**
 * 草坪View
 */
class Grass(override val x: Int, override val y: Int) : View() {
    override fun getViewImg(): String {
        return "img/grass.gif"
    }
}
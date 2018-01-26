package perseverance.li.game.model

import perseverance.li.game.enums.Direction

interface IAction {
    fun move(direction: Direction)
}
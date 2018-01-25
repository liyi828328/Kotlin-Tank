package perseverance.li.game.model

import perseverance.li.engine.core.Painter

abstract open class View {

    /**
     * x轴坐标
     */
    abstract val x: Int
    /**
     * y轴坐标
     */
    abstract val y: Int

    /**
     * 元素的宽度
     */
    fun getViewWidth(): Int {
        return Painter.size(getViewImg())[0]
    }

    /**
     * 元素的高度
     */
    fun getViewHeight(): Int {
        return Painter.size(getViewImg())[1]
    }

    abstract open fun getViewImg(): String

    fun draw() {
        Painter.drawImage(getViewImg(), x, y)
    }

}
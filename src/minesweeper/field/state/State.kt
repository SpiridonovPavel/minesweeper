package minesweeper.field.state

import minesweeper.field.Field

sealed class State(protected val context: Field) {

    abstract val terminal: Boolean

    abstract fun explore(x: Int, y: Int)
}

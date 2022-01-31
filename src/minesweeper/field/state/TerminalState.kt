package minesweeper.field.state

import minesweeper.field.Field

sealed class TerminalState(context: Field) : State(context) {

    override val terminal = true

    override fun explore(x: Int, y: Int) {
        //NOOP
    }
}

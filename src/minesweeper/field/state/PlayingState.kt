package minesweeper.field.state

import minesweeper.cell.MineCell
import minesweeper.cell.visitor.ExploreCellVisitor
import minesweeper.field.Field

class PlayingState(context: Field) : State(context) {

    override val terminal = false
    private val exploreVisitor = ExploreCellVisitor(context)

    override fun explore(x: Int, y: Int) {
        if (context.cells[x][y] is MineCell) {
            context.state = LostState(context)
            openAllMines()
        } else {
            context.cells[x][y].accept(exploreVisitor)
        }
    }

    private fun openAllMines() {
        for ((x, y) in context.mines) {
            context.cells[x][y].open = true
        }
    }
}

package minesweeper.field.state

import minesweeper.cell.EmptyCell
import minesweeper.cell.HintCell
import minesweeper.cell.MineCell
import minesweeper.field.Field
import kotlin.math.max
import kotlin.random.Random

class BlankState(context: Field) : State(context) {

    override val terminal = false

    override fun explore(x: Int, y: Int) {
        generateField(x, y)
        context.state = PlayingState(context)
        context.state.explore(x, y)
        setMarks()
    }

    private fun generateField(firstX: Int, firstY: Int) {
        val random = Random.Default
        var x: Int
        var y: Int
        repeat(context.minesCount) {
            do {
                x = random.nextInt(0, context.cellsX)
                y = random.nextInt(0, context.cellsY)
            } while (context.cells[x][y] is MineCell || (x == firstX && y == firstY))
            context.cells[x][y] = MineCell(x, y)
            context.mines.add(Pair(x, y))
        }

        setHints()
    }

    private fun setHints() {
        for ((x, y) in context.mines) {
            for (i in max(0, x - 1)..Integer.min(context.cellsX - 1, x + 1)) {
                for (j in max(0, y - 1)..Integer.min(context.cellsY - 1, y + 1)) {
                    when (val cell = context.cells[i][j]) {
                        is EmptyCell -> context.cells[i][j] = HintCell(i, j, 1)
                        is HintCell -> context.cells[i][j] = HintCell(i, j, cell.number + 1)
                    }
                }
            }
        }
    }

    private fun setMarks() {
        val copy = context.marks.toSet()
        for ((x, y) in copy) {
            val cell = context.cells[x][y]
            if (cell.open) {
                cell.marked = false
                context.marks.remove(Pair(x, y))
            } else {
                cell.marked = true
            }
        }
    }
}

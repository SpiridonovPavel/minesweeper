package minesweeper.cell.visitor

import minesweeper.cell.Cell
import minesweeper.cell.EmptyCell
import minesweeper.cell.HintCell
import minesweeper.cell.MineCell

class DrawCellVisitor : CellVisitor() {

    override fun visitEmptyCell(cell: EmptyCell) {
        draw(cell) { EMPTY_OPEN_CELL }
    }

    override fun visitHintCell(cell: HintCell) {
        draw(cell) { cell.number.digitToChar() }
    }

    override fun visitMineCell(cell: MineCell) {
        draw(cell) { MINE_OPEN_CELL }
    }

    private fun <T : Cell> draw(cell: T, drawOpen: (T) -> Char) =
        print(
            when {
                cell.open -> drawOpen(cell)
                cell.marked -> MARKED_CELL
                else -> HIDDEN_CELL
            }
        )

    companion object {
        const val HIDDEN_CELL = '.'
        const val EMPTY_OPEN_CELL = '/'
        const val MINE_OPEN_CELL = 'X'
        const val MARKED_CELL = '*'
    }
}

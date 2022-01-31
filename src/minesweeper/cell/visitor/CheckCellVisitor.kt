package minesweeper.cell.visitor

import minesweeper.cell.Cell
import minesweeper.cell.EmptyCell
import minesweeper.cell.HintCell
import minesweeper.cell.MineCell
import minesweeper.field.Field
import kotlin.math.max
import kotlin.math.min

class CheckCellVisitor(val field: Field) : CellVisitor() {

    private val exploreCellVisitor = ExploreCellVisitor(field)

    override fun visitEmptyCell(cell: EmptyCell) {
        //NOOP
    }

    override fun visitHintCell(cell: HintCell) {
        if (cell.open && allSurroundingCellsCanBeOpened(cell)) {
            exploreAroundCell(cell)
        }
    }

    override fun visitMineCell(cell: MineCell) {
        //NOOP
    }

    private fun allSurroundingCellsCanBeOpened(cell: Cell): Boolean {
        for (i in max(0, cell.x - 1)..Integer.min(field.cellsX - 1, cell.x + 1)) {
            for (j in max(0, cell.y - 1)..Integer.min(field.cellsY - 1, cell.y + 1)) {
                if (field.cells[i][j] is MineCell) {
                    if (!field.cells[i][j].marked) return false
                } else if (field.cells[i][j].marked) return false
            }
        }
        return true
    }

    private fun exploreAroundCell(cell: Cell) {
        exploreCell(max(0, cell.x - 1), max(0, cell.y - 1))
        exploreCell(max(0, cell.x - 1), cell.y)
        exploreCell(max(0, cell.x - 1), min(field.cellsY - 1, cell.y + 1))
        exploreCell(cell.x, max(0, cell.y - 1))
        exploreCell(cell.x, min(field.cellsY - 1, cell.y + 1))
        exploreCell(min(field.cellsX - 1, cell.x + 1), max(0, cell.y - 1))
        exploreCell(min(field.cellsX - 1, cell.x + 1), cell.y)
        exploreCell(min(field.cellsX - 1, cell.x + 1), min(field.cellsY - 1, cell.y + 1))
    }

    private fun exploreCell(x: Int, y: Int) {
        val cell = field.cells[x][y]
        if (cell !is MineCell && !cell.open) {
            cell.accept(exploreCellVisitor)
        }
    }
}

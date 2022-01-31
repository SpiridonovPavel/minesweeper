package minesweeper.cell.visitor

import minesweeper.cell.Cell
import minesweeper.cell.EmptyCell
import minesweeper.cell.HintCell
import minesweeper.cell.MineCell
import minesweeper.field.Field
import java.util.*
import kotlin.math.max
import kotlin.math.min

class ExploreCellVisitor(val field: Field) : CellVisitor() {

    override fun visitEmptyCell(cell: EmptyCell) {
        exploreCell(cell.x, cell.y)
    }

    override fun visitHintCell(cell: HintCell) {
        exploreCell(cell.x, cell.y)
    }

    override fun visitMineCell(cell: MineCell) {
        //NOOP
    }

    private fun exploreCell(x: Int, y: Int) {
        val stack = Stack<Cell>()
        stack.push(field.cells[x][y])
        while (!stack.isEmpty()) {
            val cell = stack.pop()
            if (!cell.open) {
                cell.open = true
                if (!cell.marked) {
                    field.notMarkedAndNotOpenCellCount--
                } else {
                    cell.marked = false
                }
                if (cell is EmptyCell) {
                    exploreEmptyAroundCell(cell, stack)
                }
            }
        }
    }

    private fun exploreEmptyAroundCell(cell: Cell, stack: Stack<Cell>) {
        stack.push(field.cells[max(0, cell.x - 1)][max(0, cell.y - 1)])
        stack.push(field.cells[max(0, cell.x - 1)][cell.y])
        stack.push(field.cells[max(0, cell.x - 1)][min(field.cellsY - 1, cell.y + 1)])
        stack.push(field.cells[cell.x][max(0, cell.y - 1)])
        stack.push(field.cells[cell.x][min(field.cellsY - 1, cell.y + 1)])
        stack.push(field.cells[min(field.cellsX - 1, cell.x + 1)][max(0, cell.y - 1)])
        stack.push(field.cells[min(field.cellsX - 1, cell.x + 1)][cell.y])
        stack.push(field.cells[min(field.cellsX - 1, cell.x + 1)][min(field.cellsY - 1, cell.y + 1)])
    }
}

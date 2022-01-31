package minesweeper.cell

import minesweeper.cell.visitor.CellVisitor

class EmptyCell(x: Int, y: Int) : Cell(x, y) {
    override fun accept(visitor: CellVisitor) {
        visitor.visitEmptyCell(this)
    }
}

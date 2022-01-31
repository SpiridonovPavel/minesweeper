package minesweeper.cell

import minesweeper.cell.visitor.CellVisitor

class MineCell(x: Int, y: Int) : Cell(x, y) {
    override fun accept(visitor: CellVisitor) {
        visitor.visitMineCell(this)
    }
}

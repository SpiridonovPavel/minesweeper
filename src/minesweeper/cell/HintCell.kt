package minesweeper.cell

import minesweeper.cell.visitor.CellVisitor

class HintCell(x: Int, y: Int, var number: Int) : Cell(x, y) {
    override fun accept(visitor: CellVisitor) {
        visitor.visitHintCell(this)
    }

    fun increment() {
        number = number++
    }
}

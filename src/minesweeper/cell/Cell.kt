package minesweeper.cell

import minesweeper.cell.visitor.CellVisitor

sealed class Cell(val x: Int, val y: Int) {
    var open = false
    var marked = false

    abstract fun accept(visitor: CellVisitor)
}

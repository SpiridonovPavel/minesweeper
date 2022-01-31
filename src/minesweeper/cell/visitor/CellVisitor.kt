package minesweeper.cell.visitor

import minesweeper.cell.EmptyCell
import minesweeper.cell.HintCell
import minesweeper.cell.MineCell

abstract class CellVisitor {
    abstract fun visitEmptyCell(cell: EmptyCell)
    abstract fun visitHintCell(cell: HintCell)
    abstract fun visitMineCell(cell: MineCell)
}

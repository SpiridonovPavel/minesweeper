package minesweeper.field

import minesweeper.cell.Cell
import minesweeper.cell.EmptyCell
import minesweeper.cell.visitor.CheckCellVisitor
import minesweeper.cell.visitor.DrawCellVisitor
import minesweeper.field.state.BlankState
import minesweeper.field.state.State
import minesweeper.field.state.WonState

class Field(val minesCount: Int, val cellsX: Int, val cellsY: Int = cellsX) {

    val cells: Array<Array<Cell>> = Array(cellsX) { emptyArray() }

    init {
        for (x in 0 until cellsX) {
            cells[x] = Array(cellsY) { EmptyCell(x, it) }
        }
    }

    private var drawCellVisitor = DrawCellVisitor()
    private var checkCellVisitor = CheckCellVisitor(this)

    var state: State = BlankState(this)
    val marks = mutableSetOf<Pair<Int, Int>>()
    val mines = mutableSetOf<Pair<Int, Int>>()
    var notMarkedAndNotOpenCellCount = cellsX * cellsY

    fun drawField() {
        println(" │123456789│")
        println("—│—————————│")
        for (y in 0 until cellsY) {
            print("${y + 1}│")
            for (x in 0 until cellsX) {
                cells[x][y].accept(drawCellVisitor)
            }
            println("│")
        }
        println("—│—————————│")
    }

    fun checkCell(x: Int, y: Int) {
        cells[x][y].accept(checkCellVisitor)
    }

    fun markCell(x: Int, y: Int) {
        val cell = cells[x][y]
        when {
            cell.open -> println("There is a number here!")
            cell.marked -> {
                cell.marked = false
                notMarkedAndNotOpenCellCount++
                marks.remove(Pair(cell.x, cell.y))
            }
            else -> {
                cell.marked = true
                notMarkedAndNotOpenCellCount--
                marks.add(Pair(cell.x, cell.y))
            }
        }
    }

    fun checkAllMinesDetected() {
        if (mines == marks || mines.size == marks.size + notMarkedAndNotOpenCellCount) {
            state = WonState(this)
        }
    }
}


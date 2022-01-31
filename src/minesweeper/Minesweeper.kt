package minesweeper

import minesweeper.field.Field
import minesweeper.field.state.LostState
import minesweeper.field.state.WonState

class Minesweeper(minesCount: Int, cellsX: Int, cellsY: Int = cellsX) {

    val field = Field(minesCount, cellsX, cellsY)

    fun start() {
        while (!field.state.terminal) {
            field.drawField()
            println("Set/unset mine marks or claim a cell as free:")
            val (x, y, command) = readLine()!!.split(" ")
            val cellX = x.toInt() - 1
            val cellY = y.toInt() - 1
            if (cellX in 0 until field.cellsX || cellY in 0 until field.cellsY) {
                when (command) {
                    "f", "free" -> {
                        field.state.explore(cellX, cellY)
                    }
                    "m", "mine" -> {
                        field.markCell(cellX, cellY)
                    }
                    "c", "check" -> {
                        field.checkCell(cellX, cellY)
                    }
                    else -> {
                        println("Unsupported command")
                    }
                }
                field.checkAllMinesDetected()
            }
        }
        field.drawField()
        when (field.state) {
            is WonState -> println("Congratulations! You found all the mines!")
            is LostState -> println("You stepped on a mine and failed!")
        }
    }
}

package minesweeper

const val CELLS_X = 9
const val CELLS_Y = 9

fun main() {
    println("How many mines do you want on the field?")
    val minesCount = readLine()!!.toInt()
    Minesweeper(minesCount, CELLS_X, CELLS_Y).start()
}

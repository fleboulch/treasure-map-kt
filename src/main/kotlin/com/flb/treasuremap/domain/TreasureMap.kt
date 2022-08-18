package com.flb.treasuremap.domain

data class TreasureMap(var explorer: Explorer) {

    fun run() {
        explorer.goHead()
    }
}

data class Explorer(var position: Position) {

    fun goHead() {
        position = Position(position.next(), position.orientation)
    }

}

data class Position(val value: Pair<Int, Int>, val orientation: Orientation) {

    fun next(): Pair<Int, Int> =
        Pair(
            value.first + orientation.actionX,
            value.second + orientation.actionY
        )

}

enum class Orientation(
    val actionX: Int,
    val actionY: Int
) {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0)

}

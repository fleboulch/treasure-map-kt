package com.flb.treasuremap.domain

data class TreasureMap(var explorer: Explorer) {

    fun run() {
        explorer.move()
    }
}

data class Explorer(var position: Position, var movement:Movement) {

    fun move() {
        position = position.next(movement)
    }

}

data class Position(val value: Pair<Int, Int>, val orientation: Orientation) {
    fun next(movement: Movement): Position {
        if(movement == Movement.GO_AHEAD) {
            return Position(
                Pair(
                    value.first + orientation.actionX,
                    value.second + orientation.actionY
                ), orientation
            )
        }
        return Position(value, Orientation.WEST)

    }

}

enum class Movement {
    GO_AHEAD,
    RIGHT_TURN
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

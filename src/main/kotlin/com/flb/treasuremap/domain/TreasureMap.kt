package com.flb.treasuremap.domain

data class TreasureMap(var explorer: Explorer) {

    fun run() =
        explorer.move()

}

data class Explorer(var position: Position, var movement: Movement) {

    fun move() {
        position = position.next(movement)
    }

}

data class Position(val value: Pair<Int, Int>, val orientation: Orientation) {
    fun next(movement: Movement): Position =
        if (movement == Movement.GO_AHEAD) {
            Position(
                Pair(
                    value.first + orientation.actionX,
                    value.second + orientation.actionY
                ), orientation
            )
        } else Position(value, orientation.next(movement))

}

enum class Movement {
    GO_AHEAD,
    RIGHT_TURN,
    LEFT_TURN
}

enum class Orientation(
    val degree: Int,
    val actionX: Int,
    val actionY: Int
) {
    NORTH(0, 0, -1),
    SOUTH(180, 0, 1),
    EAST(90, 1, 0),
    WEST(270, -1, 0);

    fun next(movement: Movement): Orientation {
        val newDegree: Int = getNewDegree(movement)
        return values().find { it.degree == newDegree }!!
    }

    private fun getNewDegree(movement: Movement): Int =
        if (movement == Movement.RIGHT_TURN) (degree + 90) % 360
        else (degree - 90 + 360) % 360


}

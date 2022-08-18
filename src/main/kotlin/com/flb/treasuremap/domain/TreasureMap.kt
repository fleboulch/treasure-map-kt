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

    fun next(): Pair<Int, Int> {
        if (orientation == Orientation.EAST) {
            return Pair(1, 0)
        }
        if (orientation == Orientation.WEST) {
            return Pair(0, 0)
        }
        if (orientation == Orientation.SOUTH) {
            return Pair(0, 1)
        }
        return Pair(1, 0)
    }


}

enum class Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST
}

package com.flb.treasuremap.domain

data class TreasureMap(var explorer: Explorer) {

    fun run() {
        explorer.goHead()
    }
}

data class Explorer(var position: Position) {

    fun goHead() {
        position = Position(position.next())
    }

}

data class Position(val value: Pair<Int, Int>) {

    fun next(): Pair<Int, Int> =
        Pair(1, 0)


}

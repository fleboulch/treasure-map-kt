package com.flb.treasuremap.domain

import com.flb.treasuremap.domain.Movement.GoAhead
import com.flb.treasuremap.domain.Movement.LeftTurn
import com.flb.treasuremap.domain.Movement.RightTurn
data class TreasureMap(var explorer: Explorer) {

    fun run(): Unit =
        explorer.move()

}

data class Explorer(var position: Position, var movement: Movement) {

    fun move() {
        position += movement
    }

}

data class Position(val value: Pair<Int, Int>, val orientation: Orientation) {

    operator fun plus(movement: Movement): Position =
        movement.move(this)

}

operator fun Pair<Int, Int>.plus(orientation: Orientation): Pair<Int, Int> =
    Pair(first + orientation.actionX, second + orientation.actionY)

sealed class Movement {

    abstract fun move(position: Position): Position

    object GoAhead : Movement() {
        override fun move(position: Position): Position = position.copy(value = position.value + position.orientation)
    }

    object RightTurn : Movement() {
        override fun move(position: Position): Position = position.copy(orientation = position.orientation.turn(this))
    }

    object LeftTurn : Movement() {
        override fun move(position: Position): Position = position.copy(orientation = position.orientation.turn(this))
    }
}

sealed class Orientation(
    val actionX: Int,
    val actionY: Int
) {
    abstract fun turn(movement: Movement): Orientation

    object North : Orientation(0, -1) {
        override fun turn(movement: Movement): Orientation =
            when (movement) {
                GoAhead -> North
                RightTurn -> East
                LeftTurn -> West
            }
    }

    object East : Orientation(1, 0) {
        override fun turn(movement: Movement): Orientation =
            when (movement) {
                GoAhead -> East
                RightTurn -> South
                LeftTurn -> North
            }
    }

    object South : Orientation(0, 1) {
        override fun turn(movement: Movement): Orientation =
            when (movement) {
                GoAhead -> South
                RightTurn -> West
                LeftTurn -> East
            }
    }

    object West : Orientation(-1, 0) {
        override fun turn(movement: Movement): Orientation =
            when (movement) {
                GoAhead -> West
                RightTurn -> North
                LeftTurn -> South
            }
    }
}
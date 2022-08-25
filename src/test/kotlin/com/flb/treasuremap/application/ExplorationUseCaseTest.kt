package com.flb.treasuremap.application

import com.flb.treasuremap.domain.*
import com.flb.treasuremap.domain.Movement.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExplorationUseCaseTest {

    private val useCase: ExplorationUseCase = ExplorationUseCase()

    @ParameterizedTest
    @CsvSource(
        value = [
            "0,0,SOUTH,0,1",
            "1,1,NORTH,1,0",
            "0,0,EAST,1,0",
            "1,0,WEST,0,0"
        ]
    )
    fun an_explorer_should_go_ahead(
        inputX: Int,
        inputY: Int,
        inputCoordinates: String,
        outputX: Int,
        outputY: Int
    ) {
        // given
        val orientation = buildOrientation(inputCoordinates)
        val treasureMap = TreasureMap(Explorer(Position(Pair(inputX, inputY), orientation), GoAhead))

        // when
        useCase.handle(treasureMap)

        // then
        assertThat(treasureMap).isEqualTo(
            TreasureMap(Explorer(Position(Pair(outputX, outputY), orientation), GoAhead))
        )
    }

    private fun buildOrientation(inputCoordinates: String): Orientation =
        when (inputCoordinates) {
            "SOUTH" -> Orientation.South
            "NORTH" -> Orientation.North
            "EAST" -> Orientation.East
            "WEST" -> Orientation.West
            else -> {throw RuntimeException("toto")}
        }


    @ParameterizedTest
    @CsvSource(
        value = [
            "SOUTH,WEST",
            "WEST,NORTH",
            "NORTH,EAST",
            "EAST,SOUTH",
        ]
    )
    fun an_explorer_should_turn_to_the_right(inputCoordinates: String, outputCoordinates: String) {
        // given
        val movement = RightTurn
        val treasureMap =
            TreasureMap(Explorer(Position(Pair(0, 0), buildOrientation(inputCoordinates)), movement))

        // when
        useCase.handle(treasureMap)

        // then
        assertThat(treasureMap).isEqualTo(
            TreasureMap(Explorer(Position(Pair(0, 0), buildOrientation(outputCoordinates)), movement))
        )
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "SOUTH,EAST",
            "EAST,NORTH",
            "NORTH,WEST",
            "WEST,SOUTH"
        ]
    )
    fun an_explorer_should_turn_to_the_left(inputCoordinates: String,outputCoordinates: String) {
        // given
        val movement = LeftTurn
        val treasureMap =
            TreasureMap(Explorer(Position(Pair(0, 0), buildOrientation(inputCoordinates)), movement))

        // when
        useCase.handle(treasureMap)

        // then
        assertThat(treasureMap).isEqualTo(
            TreasureMap(Explorer(Position(Pair(0, 0), buildOrientation(outputCoordinates)), movement))
        )
    }

}

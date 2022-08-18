package com.flb.treasuremap.application

import com.flb.treasuremap.domain.Explorer
import com.flb.treasuremap.domain.Orientation
import com.flb.treasuremap.domain.Position
import com.flb.treasuremap.domain.TreasureMap
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
    fun an_explorer_should_go_ahead_horizontally(
        inputX: Int,
        inputY: Int,
        inputCoordinates: String,
        outputX: Int,
        outputY: Int
    ) {
        // given
        val orientation = Orientation.valueOf(inputCoordinates)
        val treasureMap = TreasureMap(Explorer(Position(Pair(inputX, inputY), orientation)))

        // when
        useCase.handle(treasureMap)

        // then
        assertThat(treasureMap).isEqualTo(
            TreasureMap(Explorer(Position(Pair(outputX, outputY), orientation)))
        )
    }

}

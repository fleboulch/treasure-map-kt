package com.flb.treasuremap.application

import com.flb.treasuremap.domain.Explorer
import com.flb.treasuremap.domain.Position
import com.flb.treasuremap.domain.TreasureMap
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExplorationUseCaseTest {

    private val useCase: ExplorationUseCase = ExplorationUseCase()

    @Test
    fun an_explorer_should_go_ahead() {
        // given
        val treasureMap = TreasureMap(Explorer(Position(Pair(0, 0))))

        // when
        useCase.handle(treasureMap)

        // then
        assertThat(treasureMap).isEqualTo(
            TreasureMap(Explorer(Position(Pair(1, 0))))
        )
    }
}

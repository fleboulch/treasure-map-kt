package com.flb.treasuremap.application

import com.flb.treasuremap.domain.TreasureMap
import org.springframework.stereotype.Service

@Service
class ExplorationUseCase {

    fun handle(treasureMap: TreasureMap) = treasureMap.run()
}
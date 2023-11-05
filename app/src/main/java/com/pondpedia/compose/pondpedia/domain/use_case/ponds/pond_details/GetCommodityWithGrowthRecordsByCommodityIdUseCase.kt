package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetCommodityWithGrowthRecordsByCommodityIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        commodityId: Long
    ): Flow<CommodityWithGrowthRecords> {
        return repository.getCommodityWithGrowthRecordsByCommodityId(commodityId)
    }
}

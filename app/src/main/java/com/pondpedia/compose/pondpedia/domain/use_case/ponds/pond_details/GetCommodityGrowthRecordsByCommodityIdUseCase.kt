package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetCommodityGrowthRecordsByCommodityIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        commodityId: Long
    ): Flow<List<CommodityGrowthRecords>> {
        return repository.getCommodityWithGrowthRecordsByCommodityId(commodityId)
    }
}

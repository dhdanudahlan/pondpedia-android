package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCommodityHealthRecordsByCommodityIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        pondId: Long
    ): Flow<List<CommodityHealthRecords>> {
        return repository.getCommodityWithHealthRecordsByCommodityId(pondId)
    }
}

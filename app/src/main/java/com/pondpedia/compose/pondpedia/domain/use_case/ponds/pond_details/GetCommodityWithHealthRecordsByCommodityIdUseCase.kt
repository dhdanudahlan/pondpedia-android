package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithHealthRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetCommodityWithHealthRecordsByCommodityIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        pondId: Long
    ): Flow<CommodityWithHealthRecords> {
        return repository.getCommodityWithHealthRecordsByCommodityId(pondId)
    }
}

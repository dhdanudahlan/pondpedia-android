package com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetCommodityHealthRecordsByCommodityIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        pondId: Long
    ): Flow<List<CommodityHealthRecords>> {
        return repository.getCommodityWithHealthRecordsByCommodityId(pondId)
    }
}

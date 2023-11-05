package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.domain.model.pond_management.WaterRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetWaterRecordsByPondIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        pondId: Long
    ): Flow<List<WaterRecords>> {
        return repository.getWaterRecordsByPondId(pondId)
    }
}

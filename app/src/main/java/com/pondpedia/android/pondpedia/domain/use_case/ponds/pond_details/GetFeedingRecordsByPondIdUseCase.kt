package com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.android.pondpedia.domain.model.pond_management.FeedingRecords
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetFeedingRecordsByPondIdUseCase (
    private val repository: PondDetailsRepository
) {
    operator fun invoke(
        pondId: Long
    ): Flow<List<FeedingRecords>> {
        return repository.getFeedingRecordsByPondId(pondId)
    }
}

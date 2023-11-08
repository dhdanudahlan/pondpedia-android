package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.pond_management.FeedingRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository

class AddFeedingRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(feedingRecords: FeedingRecords = FeedingRecords(0, DateGenerator.getCurrentDateTime(), "0", "", 0, 0)) {
        repository.insertFeedingRecords(
            feedingRecords = feedingRecords,
        )
    }
}

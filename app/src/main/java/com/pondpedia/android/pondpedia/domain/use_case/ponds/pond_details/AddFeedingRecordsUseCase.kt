package com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository

class AddFeedingRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(feedingRecords: FeedingRecords = FeedingRecords(0, DateGenerator.getCurrentDateTime(), "0", "", 0, 0)) {
        repository.insertFeedingRecords(
            feedingRecords = feedingRecords,
        )
    }
}

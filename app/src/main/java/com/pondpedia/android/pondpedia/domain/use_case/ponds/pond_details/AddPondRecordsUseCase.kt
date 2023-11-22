package com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository

class AddPondRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(pondRecords: PondRecords = PondRecords(0, DateGenerator.getCurrentDateTime(), "0", "", 0)) {
        repository.insertPondRecords(
            pondRecords = pondRecords,
        )
    }
}

package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.pond_management.WaterRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository

class AddWaterRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(waterRecords: WaterRecords = WaterRecords(0, DateGenerator.getCurrentDateTime(), "0", "", "", "", 0)) {
        repository.insertWaterRecords(
            waterRecords = waterRecords,
        )
    }
}

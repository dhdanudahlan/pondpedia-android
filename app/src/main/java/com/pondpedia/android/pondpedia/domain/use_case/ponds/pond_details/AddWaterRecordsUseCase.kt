package com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository

class AddWaterRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(waterRecords: WaterRecords = WaterRecords(0, date = DateGenerator.getCurrentDateTime(), color = "", note = "", pondId = 0)) =
        repository.insertWaterRecords(
            waterRecords = waterRecords,
        )

}

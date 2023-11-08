package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository

class AddCommodityHealthRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(commodityHealthRecords: CommodityHealthRecords = CommodityHealthRecords(0, DateGenerator.getCurrentDateTime(), "0", "", "", "", 0)) {
        repository.insertCommodityHealthRecords(
            commodityHealthRecords = commodityHealthRecords,
        )
    }
}

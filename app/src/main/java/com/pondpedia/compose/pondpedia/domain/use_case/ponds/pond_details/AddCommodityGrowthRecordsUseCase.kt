package com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository

class AddCommodityGrowthRecordsUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(commodityGrowthRecords: CommodityGrowthRecords = CommodityGrowthRecords(0, DateGenerator.getCurrentDateTime(), "0", "", "", "", 0)) {
        repository.insertCommodityGrowthRecords(
            commodityGrowthRecords = commodityGrowthRecords,
        )
    }
}

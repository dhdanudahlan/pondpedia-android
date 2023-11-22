package com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details

import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository

class AddCommodityUseCase (
    private val repository: PondDetailsRepository
) {
    suspend operator fun invoke(commodity: Commodity = Commodity(0, DateGenerator.getCurrentDateTime(), "0", "", "", "", 0)) {
        repository.insertCommodity(
            commodity = commodity,
        )
    }
}

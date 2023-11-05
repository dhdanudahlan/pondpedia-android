package com.pondpedia.compose.pondpedia.domain.use_case.ponds

import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondsRepository

class AddPondUseCase (
    private val repository: PondsRepository
) {
    suspend operator fun invoke(pond: Pond, pondRecords: PondRecords = PondRecords(0, DateGenerator.getCurrentDateTime(), "0", "", pond.pondId), categoryList: List<String> = listOf("-")) {
        repository.addPond(
            pond = pond,
            pondRecords = pondRecords,
            categoryList = categoryList
        )
    }
}

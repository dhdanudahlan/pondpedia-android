package com.pondpedia.compose.pondpedia.domain.use_case.ponds

import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.repository.PondsRepository
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.SortType
import kotlinx.coroutines.flow.Flow

class GetPondListByCategoryUseCase (
    private val repository: PondsRepository
) {
    operator fun invoke(
        sortType: SortType = SortType.CREATED_DATE,
        categoryName: String = "-",
    ): Flow<List<Pond>> {
        return repository.getPondsByCategory(sortType, categoryName)
    }
}

package com.pondpedia.android.pondpedia.domain.use_case.ponds

import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.repository.PondsRepository
import com.pondpedia.android.pondpedia.presentation.screens.home.ponds.components.viewmodel.SortType
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

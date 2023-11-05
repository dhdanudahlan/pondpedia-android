package com.pondpedia.compose.pondpedia.domain.use_case.ponds

import com.pondpedia.compose.pondpedia.domain.model.pond_management.Category
import com.pondpedia.compose.pondpedia.domain.repository.PondsRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryListUseCase (
    private val repository: PondsRepository
) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategoryList()
    }
}

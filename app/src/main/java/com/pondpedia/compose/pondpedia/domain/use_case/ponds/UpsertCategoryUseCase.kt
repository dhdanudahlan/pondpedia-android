package com.pondpedia.compose.pondpedia.domain.use_case.ponds

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.domain.repository.PondsRepository

class UpsertCategoryUseCase (
    private val repository: PondsRepository
) {
    suspend operator fun invoke(categoryEntity: CategoryEntity = CategoryEntity("-")) {
        repository.upsertCategory(categoryEntity)
    }
}

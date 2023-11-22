package com.pondpedia.android.pondpedia.domain.use_case.ponds

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.domain.repository.PondsRepository

class UpsertCategoryUseCase (
    private val repository: PondsRepository
) {
    suspend operator fun invoke(categoryEntity: CategoryEntity = CategoryEntity("-")) {
        repository.upsertCategory(categoryEntity)
    }
}

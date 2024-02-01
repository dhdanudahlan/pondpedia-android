package com.pondpedia.android.pondpedia.domain.repository

import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.SortType
import kotlinx.coroutines.flow.Flow

interface PondsRepository {

    fun getPondsByCategory(
        sortType: SortType,
        categoryName: String,
    ): Flow<List<Pond>>

    fun getPondById(
        pondId: Long
    ): Flow<List<Pond>>

    fun getCategoryList(
    ): Flow<List<Category>>

    suspend fun addPond(
        pond: Pond,
        pondRecords: PondRecords,
        categoryList: List<String> = listOf()
    ): Resource<Unit>

    suspend fun addCategory(
        category: Category
    )

    suspend fun addPondCategoryCrossRef(
        pondCategoryCrossRef: PondCategoryCrossRefEntity
    )

    suspend fun deletePondById(
        pondId: Long
    )

    suspend fun deleteCategoryName(
        categoryName: String
    )

    suspend fun deletePondCategoryRefById(
        pondId: Long,
        categoryName: String
    )

    suspend fun upsertCategory(categoryEntity: CategoryEntity)
}
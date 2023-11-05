package com.pondpedia.compose.pondpedia.domain.repository

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Category
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.SortType
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
    )

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
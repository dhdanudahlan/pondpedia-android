package com.pondpedia.compose.pondpedia.domain.repository

import androidx.lifecycle.LiveData
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Category
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.pond_management.PondRecords

interface PondRepository {

    fun getPondsByCategory(
        categoryId: Long,
    ): LiveData<List<Pond>>

    fun getPondById(
        pondId: Long
    ): Pond

    fun getCategoryNames(
    ): LiveData<List<String>>

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

    suspend fun deletePondCategoryRef(
        pondId: Long,
        categoryName: String
    )
}
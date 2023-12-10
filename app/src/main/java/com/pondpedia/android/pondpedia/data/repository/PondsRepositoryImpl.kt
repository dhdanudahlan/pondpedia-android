package com.pondpedia.android.pondpedia.data.repository

import com.pondpedia.android.pondpedia.data.local.dao.PondDetailsDao
import com.pondpedia.android.pondpedia.data.local.dao.PondsDao
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.repository.PondsRepository
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.SortType
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PondsRepositoryImpl(
    private val pondsDao: PondsDao,
    private val pondDetailsDao: PondDetailsDao
): PondsRepository {
    override fun getPondsByCategory(
        sortType: SortType,
        categoryName: String
    ): Flow<List<Pond>> {
        val pondList = pondsDao.getPondsOfCategory(categoryName)
            .map { categoryWithPonds ->
                categoryWithPonds?.ponds?.map { it.toPond() } ?: emptyList()
            }
            .map { ponds ->
                when (sortType) {
                    SortType.NAME -> ponds.sortedBy { it.name }
                    SortType.CREATED_DATE -> ponds.sortedBy { it.createdDate }
                    SortType.UPDATED_DATE -> ponds.sortedBy { it.updatedDate }
                }
            }
        return pondList
    }

    override fun getPondById(pondId: Long): Flow<List<Pond>> {
        return pondsDao.getPondById(pondId).map { pondList ->
            pondList.map { it.toPond() }
        }
    }

    override fun getCategoryList(): Flow<List<Category>> {
        return pondsDao.getCategories().map { categoryList ->
            categoryList.map { it.toCategory() }
        }
    }

    override suspend fun addPond(pond: Pond, pondRecords: PondRecords, categoryList: List<String>) {
        val newCategoryList = categoryList.toMutableList()
        newCategoryList.add("-")

        pondsDao.createPondWithRecordsAndCategories(
            pondEntity = pond.toPondEntity(),
            pondRecordsEntity = pondRecords.toPondRecordsEntity(),
            categoryList = newCategoryList
        )
    }

    override suspend fun addCategory(category: Category) {
        pondsDao.upsertCategory(category.toCategoryEntity())
    }

    override suspend fun addPondCategoryCrossRef(pondCategoryCrossRef: PondCategoryCrossRefEntity) {
        pondsDao.insertPondCategoryCrossRef(pondCategoryCrossRef)
    }

    override suspend fun deletePondById(pondId: Long) {
        pondsDao.deletePond(pondId)
    }

    override suspend fun deleteCategoryName(categoryName: String) {
        pondsDao.deleteCategoryByName(categoryName)
    }

    override suspend fun deletePondCategoryRefById(pondId: Long, categoryName: String) {
        pondsDao.deletePondCategoryCrossRefById(pondId, categoryName)
    }

    override suspend fun upsertCategory(categoryEntity: CategoryEntity) {
        pondsDao.upsertCategory(categoryEntity)
    }
}
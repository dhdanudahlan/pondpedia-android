package com.pondpedia.compose.pondpedia.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.pondpedia.compose.pondpedia.data.local.dao.PondDao
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Category
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondRepository

class PondRepositoryImpl(
    private val dao: PondDao
): PondRepository {
    override fun getPondsByCategory(
        categoryId: Long
    ): LiveData<List<Pond>> {
        val pondList = MediatorLiveData<List<Pond>>()
        pondList.addSource(dao.getPondsOfCategory(categoryId).map { categoryWithPonds ->
                categoryWithPonds.ponds.map { it.toPond() }
        }) { newPondList ->
            pondList.value = newPondList
        }

        return pondList
    }

    override fun getPondById(pondId: Long): Pond {
        return dao.getPondById(pondId).toPond()
    }

    override fun getCategoryNames(): LiveData<List<String>> {
        val categoryList = MediatorLiveData<List<String>>()
        categoryList.addSource(dao.getCategories().map { list ->
            list.map { it.name }
        }) { newList ->
            categoryList.value = newList
        }

        return categoryList
    }

    override suspend fun addPond(pond: Pond, pondRecords: PondRecords, categoryList: List<String>) {
        val newCategoryList = categoryList.toMutableList()
        newCategoryList.add("Default")

        dao.createPondWithRecordsAndCategories(
            pondEntity = pond.toPondEntity(),
            pondRecordsEntity = pondRecords.toPondRecordsEntity(),
            categoryList = newCategoryList
        )
    }

    override suspend fun addCategory(category: Category) {
        dao.insertCategory(category.toCategoryEntity())
    }

    override suspend fun addPondCategoryCrossRef(pondCategoryCrossRef: PondCategoryCrossRefEntity) {
        dao.insertPondCategoryCrossRef(pondCategoryCrossRef)
    }

    override suspend fun deletePondById(pondId: Long) {
        dao.deletePond(pondId)
    }

    override suspend fun deleteCategoryName(categoryName: String) {
        dao.deleteCategoryByName(categoryName)
    }

    override suspend fun deletePondCategoryRef(pondId: Long, categoryName: String) {
        TODO("Not yet implemented")
    }

}
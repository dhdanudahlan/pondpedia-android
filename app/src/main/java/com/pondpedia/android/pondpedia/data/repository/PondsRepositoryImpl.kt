package com.pondpedia.android.pondpedia.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.local.dao.PondDetailsDao
import com.pondpedia.android.pondpedia.data.local.dao.PondsDao
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.repository.PondsRepository
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.SortType
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PondsRepositoryImpl(
    private val api: PondPediaApiService,
    private val pondsDao: PondsDao,
    private val pondDetailsDao: PondDetailsDao,
    private val tokenManager: TokenManager
): PondsRepository {
    override fun getPondsByCategory(
        sortType: SortType,
        categoryName: String
    ): Flow<List<Pond>> = flow {
        //while success fetch from api, show data from api otherwise show data from local db
        when(val result = api.getPonds()) {
            is NetworkResponse.Success -> {
                val pondList = result.body.docs.map { it.toPond() }
                emit(pondList)
            }

            is NetworkResponse.Error -> {
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
                    }.first()
                emit(pondList)
            }
        }
    }

    override fun getPondById(pondId: Long): Flow<List<Pond>> = flow {
        when(val result = api.getPond(pondId.toString())) {
            is NetworkResponse.Success -> {
                val pond = result.body.toPond()
                emit(listOf(pond))
            }

            is NetworkResponse.Error -> {
                val pond = pondsDao.getPondById(pondId).map { pondList ->
                    pondList.map { it.toPond() }
                }.first()
                emit(pond)
            }
        }
    }

    override fun getCategoryList(): Flow<List<Category>> {
        return pondsDao.getCategories().map { categoryList ->
            categoryList.map { it.toCategory() }
        }
    }

    override suspend fun addPond(pond: Pond, pondRecords: PondRecords, categoryList: List<String>): Resource<Unit> {
        val farmerId = tokenManager.getUserId().first().orEmpty()
        val updatedPond = pond.copy(farmerId = farmerId)

        val result = api.createPond(
            request = updatedPond.toPondRequest()
        )

        when(result) {
            is NetworkResponse.Success -> {
                val newCategoryList = categoryList.toMutableList()
                newCategoryList.add("-")

                pondsDao.createPondWithRecordsAndCategories(
                    pondEntity = pond.toPondEntity(),
                    pondRecordsEntity = pondRecords.toPondRecordsEntity(),
                    categoryList = newCategoryList
                )

                return Resource.Success(Unit)
            }
            is NetworkResponse.ServerError -> {
                val response = result.body
                return Resource.Error(response?.errors?.first()?.message ?: "Gagal menambahkan kolam", null)
            }
            is NetworkResponse.NetworkError -> {
                return Resource.Error("Cek kembali koneksi internet anda dan ulang kembali")
            }
            is NetworkResponse.UnknownError -> {
                return Resource.Error("Terjadi kesalahan pada sistem, silahkan coba lagi nanti")
            }
        }
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
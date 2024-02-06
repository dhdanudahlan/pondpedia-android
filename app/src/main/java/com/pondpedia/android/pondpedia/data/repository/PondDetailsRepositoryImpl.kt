package com.pondpedia.android.pondpedia.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.data.local.dao.PondDetailsDao
import com.pondpedia.android.pondpedia.data.local.dao.PondsDao
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PondDetailsRepositoryImpl(
    private val api: PondPediaApiService,
    private val pondsDao: PondsDao,
    private val pondDetailsDao: PondDetailsDao
): PondDetailsRepository {

    override fun getPondById(pondId: Long): Flow<List<Pond>> {
        return pondsDao.getPondById(pondId).map { pondList ->
            pondList.map { it.toPond() }
        }
    }

    override fun getPondRecordsByPondId(pondId: Long): Flow<List<PondRecords>>{
        return pondDetailsDao.getPondWithPondRecords(pondId).map { pondWithRecords ->
            pondWithRecords?.pondRecords?.map { it.toPondRecords() } ?: emptyList()
        }
    }

    override fun getWaterRecordsByPondId(pondId: Long): Flow<List<WaterRecords>> = flow {
        when(val result = api.getWaterRecords(pondId)) {
            is NetworkResponse.Success -> {
                val waterRecords = result.body.data.map { it.toWaterRecords() }
                emit(waterRecords)
            }
            is NetworkResponse.Error -> {
                val waterRecords = pondDetailsDao.getPondWithWaterRecords(pondId)
                    .map { pondWithWaterRecords ->
                        pondWithWaterRecords?.waterRecords?.map { it.toWaterRecords() } ?: emptyList()
                    }.firstOrNull()
                emit(waterRecords.orEmpty())
            }
        }
    }

    override fun getFeedingRecordsByPondId(pondId: Long): Flow<List<FeedingRecords>> {
        return pondDetailsDao.getPondWithFeedingRecords(pondId).map { pondWithFeedingRecords ->
            pondWithFeedingRecords?.feedingRecords?.map { it.toFeedingRecords() } ?: emptyList()
        }
    }

    override fun getCommodityListByPondId(pondId: Long): Flow<List<Commodity>> {
        return pondDetailsDao.getPondWithCommodities(pondId).map { pondWithCommodities ->
            pondWithCommodities?.commodities?.map { it.toCommodity() } ?: emptyList()
        }
    }

    override fun getCommodityWithGrowthRecordsByCommodityId(commodityId: Long): Flow<List<CommodityGrowthRecords>> {
        return pondDetailsDao.getCommodityWithGrowthRecords(commodityId).map { commodityWithGrowthRecords ->
            commodityWithGrowthRecords?.growthRecords?.map { it.toCommodityGrowthRecords() } ?: emptyList()
        }
    }

    override fun getCommodityWithHealthRecordsByCommodityId(commodityId: Long): Flow<List<CommodityHealthRecords>> {
        return pondDetailsDao.getCommodityWithHealthRecords(commodityId).map { commodityWithHealthRecords ->
            commodityWithHealthRecords?.healthRecords?.map { it.toCommodityHealthRecords() } ?: emptyList()
        }
    }

    override suspend fun insertPondRecords(pondRecords: PondRecords): Long {
        return pondsDao.insertPondRecords(pondRecords.toPondRecordsEntity())
    }

    override suspend fun insertWaterRecords(waterRecords: WaterRecords): Resource<Unit> {
        return when(val result = api.addWaterRecord(waterRecords.pondId, waterRecords.toWaterRequest())) {
            is NetworkResponse.Success -> {
                val response = result.body.data
                val waterRecordsUpdated = waterRecords.copy(recordId = response.id)
                pondDetailsDao.insertWaterRecords(waterRecordsUpdated.toWaterRecordsEntity())
                Resource.Success(Unit)
            }

            is NetworkResponse.ServerError -> {
                val response = result.body
                Resource.Error(response?.errors?.first()?.message ?: "Gagal menambahkan data air")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error("Cek kembali koneksi internet anda dan ulang kembali")
            }
            is NetworkResponse.UnknownError -> {
                Resource.Error("Terjadi kesalahan pada sistem, silahkan coba lagi nanti")
            }
        }
    }

    override suspend fun insertFeedingRecords(feedingRecords: FeedingRecords): Long {
        return pondDetailsDao.insertFeedingRecords(feedingRecords.toFeedingRecordsEntity())
    }

    override suspend fun insertCommodity(commodity: Commodity): Long {
        return pondDetailsDao.insertCommodity(commodity.toCommodityEntity())
    }

    override suspend fun insertCommodityGrowthRecords(commodityGrowthRecords: CommodityGrowthRecords): Long {
        return pondDetailsDao.insertCommodityGrowthRecords(commodityGrowthRecords.toCommodityGrowthRecordsEntity())
    }

    override suspend fun insertCommodityHealthRecords(commodityHealthRecords: CommodityHealthRecords): Long {
        return pondDetailsDao.insertCommodityHealthRecords(commodityHealthRecords.toCommodityHealthRecordsEntity())
    }

    override suspend fun deletePondById(pondId: Long) {
        pondsDao.deletePondById(pondId)
        pondsDao.deletePondRecordsByPondId(pondId)
        pondsDao.deletePondCategoryCrossRefByPondId(pondId)
    }
}

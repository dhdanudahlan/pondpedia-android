package com.pondpedia.compose.pondpedia.data.repository

import com.pondpedia.compose.pondpedia.data.local.dao.PondDetailsDao
import com.pondpedia.compose.pondpedia.data.local.dao.PondsDao
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.FeedingRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.WaterRecords
import com.pondpedia.compose.pondpedia.domain.repository.PondDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PondDetailsRepositoryImpl(
    private val pondsDao: PondsDao,
    private val pondDetailsDao: PondDetailsDao
): PondDetailsRepository {

    override fun getPondById(pondId: Long): Flow<List<Pond>> {
        return pondsDao.getPondById(pondId).map { pondList ->
            pondList.map { it.toPond() }
        }
    }

    override fun getPondRecordsByPondId(pondId: Long): Flow<List<PondRecords>> {
        return pondDetailsDao.getPondWithPondRecords(pondId).map { pondWithRecords ->
            pondWithRecords?.pondRecords?.map { it.toPondRecords() } ?: emptyList()
        }
    }

    override fun getWaterRecordsByPondId(pondId: Long): Flow<List<WaterRecords>> {
        return pondDetailsDao.getPondWithWaterRecords(pondId).map { pondWithWaterRecords ->
            pondWithWaterRecords?.waterRecords?.map { it.toWaterRecords() } ?: emptyList()
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

    override suspend fun insertWaterRecords(waterRecords: WaterRecords): Long {
        return pondDetailsDao.insertWaterRecords(waterRecords.toWaterRecordsEntity())
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

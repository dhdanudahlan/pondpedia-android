package com.pondpedia.compose.pondpedia.data.repository

import com.pondpedia.compose.pondpedia.data.local.dao.PondDetailsDao
import com.pondpedia.compose.pondpedia.data.local.dao.PondsDao
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithHealthRecords
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

    override fun getCommodityWithGrowthRecordsByCommodityId(commodityId: Long): Flow<CommodityWithGrowthRecords> {
        return pondDetailsDao.getCommodityWithGrowthRecords(commodityId)
    }

    override fun getCommodityWithHealthRecordsByCommodityId(commodityId: Long): Flow<CommodityWithHealthRecords> {
        return pondDetailsDao.getCommodityWithHealthRecords(commodityId)
    }

    override suspend fun insertPondRecords(pondRecords: PondRecords): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertWaterRecords(waterRecords: WaterRecords): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertFeedingRecords(feedingRecords: FeedingRecords): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertCommodity(commodity: Commodity): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertCommodityGrowthRecords(commodityGrowthRecords: CommodityGrowthRecords): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertCommodityHealthRecords(commodityHealthRecords: CommodityHealthRecords): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deletePondById(pondId: Long) {
        TODO("Not yet implemented")
    }
}

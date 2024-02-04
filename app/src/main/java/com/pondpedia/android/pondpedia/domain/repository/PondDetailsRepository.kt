package com.pondpedia.android.pondpedia.domain.repository

import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords
import kotlinx.coroutines.flow.Flow

interface PondDetailsRepository {

     fun getPondById(pondId: Long): Flow<List<Pond>>
     
     fun getPondRecordsByPondId(pondId: Long): Flow<List<PondRecords>>
     
     fun getWaterRecordsByPondId(pondId: Long): Flow<List<WaterRecords>>
     
     fun getFeedingRecordsByPondId(pondId: Long): Flow<List<FeedingRecords>>
     
     fun getCommodityListByPondId(pondId: Long): Flow<List<Commodity>>

     fun getCommodityWithGrowthRecordsByCommodityId(commodityId: Long): Flow<List<CommodityGrowthRecords>>

     fun getCommodityWithHealthRecordsByCommodityId(commodityId: Long): Flow<List<CommodityHealthRecords>>

     suspend fun insertPondRecords(pondRecords: PondRecords): Long

     suspend fun insertWaterRecords(waterRecords: WaterRecords): Resource<Unit>

     suspend fun insertFeedingRecords(feedingRecords: FeedingRecords): Long

     suspend fun insertCommodity(commodity: Commodity): Long

     suspend fun insertCommodityGrowthRecords(commodityGrowthRecords: CommodityGrowthRecords): Long

     suspend fun insertCommodityHealthRecords(commodityHealthRecords: CommodityHealthRecords): Long

     suspend fun deletePondById(pondId: Long)
}
package com.pondpedia.compose.pondpedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityHealthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.FeedingRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.WaterRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithHealthRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithCommodities
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithFeedingRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithPondRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithWaterRecords
import kotlinx.coroutines.flow.Flow

@Dao
interface PondDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommodity(commodityEntity: CommodityEntity)
    @Transaction
    @Query("SELECT * FROM commodity_table WHERE commodityId = :commodityId")
    fun getCommodityWithGrowthRecords(commodityId: Long): Flow<CommodityWithGrowthRecords>
    @Transaction
    @Query("SELECT * FROM commodity_table WHERE commodityId = :commodityId")
    fun getCommodityWithHealthRecords(commodityId: Long): Flow<CommodityWithHealthRecords>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommodityGrowthRecords(commodityGrowthRecordsEntity: CommodityGrowthRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommodityHealthRecords(commodityHealthRecordsEntity: CommodityHealthRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedingRecords(feedingRecordsEntity: FeedingRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterRecords(waterRecordsEntity: WaterRecordsEntity)
    @Query("DELETE FROM pond_records_table WHERE pondId = :pondId")
    suspend fun deletePondRecordsByPondId(pondId: Long)
    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithCommodities(pondId: Long): Flow<PondWithCommodities>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithFeedingRecords(pondId: Long): Flow<PondWithFeedingRecords>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithPondRecords(pondId: Long): Flow<PondWithPondRecords>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithWaterRecords(pondId: Long): Flow<PondWithWaterRecords>
}
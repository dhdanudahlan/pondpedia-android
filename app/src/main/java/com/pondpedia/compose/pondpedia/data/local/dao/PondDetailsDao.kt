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
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.WaterRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithHealthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithCommoditiesEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithFeedingRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithPondRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithWaterRecordsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PondDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommodity(commodityEntity: CommodityEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommodityGrowthRecords(commodityGrowthRecordsEntity: CommodityGrowthRecordsEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommodityHealthRecords(commodityHealthRecordsEntity: CommodityHealthRecordsEntity): Long

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithCommodities(pondId: Long): Flow<PondWithCommoditiesEntity>

    @Transaction
    @Query("SELECT * FROM commodity_table WHERE commodityId = :commodityId")
    fun getCommodityWithGrowthRecords(commodityId: Long): Flow<CommodityWithGrowthRecordsEntity>

    @Transaction
    @Query("SELECT * FROM commodity_table WHERE commodityId = :commodityId")
    fun getCommodityWithHealthRecords(commodityId: Long): Flow<CommodityWithHealthRecordsEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedingRecords(feedingRecordsEntity: FeedingRecordsEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterRecords(waterRecordsEntity: WaterRecordsEntity): Long

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithFeedingRecords(pondId: Long): Flow<PondWithFeedingRecordsEntity>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithWaterRecords(pondId: Long): Flow<PondWithWaterRecordsEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPondRecords(pondRecordsEntity: PondRecordsEntity): Long
    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithPondRecords(pondId: Long): Flow<PondWithPondRecordsEntity>
    @Query("DELETE FROM pond_records_table WHERE pondId = :pondId")
    suspend fun deletePondRecordsByPondId(pondId: Long)
}
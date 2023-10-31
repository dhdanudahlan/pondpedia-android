package com.pondpedia.compose.pondpedia.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityHealthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.FeedingRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.WaterRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CategoryWithPonds
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithCategories
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithCommodities
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithFeedingRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithPondRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithWaterRecords


@Dao
interface PondDao {

    @Transaction
    fun createPondWithRecordsAndCategories(pondEntity: PondEntity, pondRecordsEntity: PondRecordsEntity, categoryList: List<String>) {
        val pondId = createPond(pondEntity)

        val pondRecordsWithPondId = pondRecordsEntity.copy(pondId = pondId)
        insertPondRecords(pondRecordsWithPondId)

        for (categoryName in categoryList) {
            val categoryId = getOrCreateCategory(categoryName)
            val newPondCategoryCrossRef = PondCategoryCrossRefEntity(pondId = pondId, categoryId = categoryId)
            insertPondCategoryCrossRef(newPondCategoryCrossRef)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createPond(pondEntity: PondEntity): Long

    @Transaction
    fun getOrCreateCategory(categoryName: String): Long {
        val existingCategory = getCategoryByName(categoryName)
        return existingCategory?.categoryId ?: insertCategory(CategoryEntity(name = categoryName))
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory (categoryEntity: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPondCategoryCrossRef (pondCategoryCrossRefEntity: PondCategoryCrossRefEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommodity(commodityEntity: CommodityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeedingRecords(feedingRecordsEntity: FeedingRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPondRecords(pondRecordsEntity: PondRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWaterRecords(waterRecordsEntity: WaterRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommodityGrowthRecords(commodityGrowthRecordsEntity: CommodityGrowthRecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommodityHealthRecords(commodityHealthRecordsEntity: CommodityHealthRecordsEntity)

    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondById(pondId: Long): PondEntity

    @Query("SELECT * FROM category_table WHERE name = :categoryName")
    fun getCategoryByName(categoryName: String): CategoryEntity

    @Query("SELECT * FROM category_table")
    fun getCategories(): LiveData<List<CategoryEntity>>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getCategoriesOfPond(pondId: Long): LiveData<PondWithCategories>

    @Transaction
    @Query("SELECT * FROM category_table WHERE categoryId = :categoryId")
    fun getPondsOfCategory(categoryId: Long): LiveData<CategoryWithPonds>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithCommodities(pondId: Long): List<PondWithCommodities>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithFeedingRecords(pondId: Long): List<PondWithFeedingRecords>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithPondRecords(pondId: Long): List<PondWithPondRecords>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondWithWaterRecords(pondId: Long): List<PondWithWaterRecords>

    @Transaction
    @Query("SELECT * FROM commodity_table WHERE commodityId = :commodityId")
    fun getCommodityWithGrowthRecords(commodityId: Long): List<CommodityWithGrowthRecords>


    @Transaction
    fun deletePond(pondId: Long) {
        deletePondRecordsByPondId(pondId)

        deletePondById(pondId)

        deletePondCategoryCrossRefByPondId(pondId)
    }

    @Transaction
    fun deletePondCategoryCrossRef(pondId: Long, categoryName: String) {
        val category = getCategoryByName(categoryName)

        deletePondCategoryCrossRefById(pondId, category.categoryId)
    }

    @Query("DELETE FROM pond_records_table WHERE pondId = :pondId")
    fun deletePondRecordsByPondId(pondId: Long)

    @Query("DELETE FROM pond_table WHERE pondId = :pondId")
    fun deletePondById(pondId: Long)

    @Query("DELETE FROM pond_category_cross_ref_table WHERE pondId = :pondId")
    fun deletePondCategoryCrossRefByPondId(pondId: Long)

    @Query("DELETE FROM category_table WHERE name = :categoryName")
    fun deleteCategoryByName(categoryName: String)

    @Query("DELETE FROM pond_category_cross_ref_table WHERE pondId = :pondId AND categoryId = :categoryId")
    fun deletePondCategoryCrossRefById(pondId: Long, categoryId: Long)

}
package com.pondpedia.compose.pondpedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CategoryWithPondsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondWithCategoriesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PondsDao {

    @Transaction
    suspend fun createPondWithRecordsAndCategories(pondEntity: PondEntity, pondRecordsEntity: PondRecordsEntity, categoryList: List<String>) {
        val pondId = createPond(pondEntity)

        val pondRecordsWithPondId = pondRecordsEntity.copy(pondId = pondId)
        insertPondRecords(pondRecordsWithPondId)

        for (categoryName in categoryList) {
            upsertCategory(CategoryEntity(categoryName = categoryName))
            val newPondCategoryCrossRef = PondCategoryCrossRefEntity(pondId = pondId, categoryName = categoryName)
            insertPondCategoryCrossRef(newPondCategoryCrossRef)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPond(pondEntity: PondEntity): Long

    @Upsert
    suspend fun upsertCategory (categoryEntity: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPondCategoryCrossRef (pondCategoryCrossRefEntity: PondCategoryCrossRefEntity): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPondRecords(pondRecordsEntity: PondRecordsEntity): Long



    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getPondById(pondId: Long): Flow<List<PondEntity>>



    @Query("SELECT * FROM category_table WHERE categoryName = :categoryName")
    fun getCategoryByName(categoryName: String): CategoryEntity

    @Query("SELECT * FROM category_table")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Transaction
    @Query("SELECT * FROM pond_table WHERE pondId = :pondId")
    fun getCategoriesOfPond(pondId: Long): Flow<PondWithCategoriesEntity>

    @Transaction
    @Query("SELECT * FROM category_table WHERE categoryName = :categoryName")
    fun getPondsOfCategory(categoryName: String): Flow<CategoryWithPondsEntity>





    @Transaction
    suspend fun deletePond(pondId: Long) {
        deletePondRecordsByPondId(pondId)

        deletePondById(pondId)

        deletePondCategoryCrossRefByPondId(pondId)
    }



    @Query("DELETE FROM pond_table WHERE pondId = :pondId")
    suspend fun deletePondById(pondId: Long)

    @Query("DELETE FROM pond_category_cross_ref_table WHERE pondId = :pondId")
    suspend fun deletePondCategoryCrossRefByPondId(pondId: Long)

    @Query("DELETE FROM category_table WHERE categoryName = :categoryName")
    suspend fun deleteCategoryByName(categoryName: String)

    @Query("DELETE FROM pond_category_cross_ref_table WHERE pondId = :pondId AND categoryName = :categoryName")
    suspend fun deletePondCategoryCrossRefById(pondId: Long, categoryName: String)

    @Query("DELETE FROM pond_records_table WHERE pondId = :pondId")
    suspend fun deletePondRecordsByPondId(pondId: Long)

}
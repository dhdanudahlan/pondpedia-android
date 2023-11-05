package com.pondpedia.compose.pondpedia.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pondpedia.compose.pondpedia.core.util.Converters
import com.pondpedia.compose.pondpedia.data.local.dao.PondDetailsDao
import com.pondpedia.compose.pondpedia.data.local.dao.PondsDao
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityHealthRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.FeedEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.FeedingRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.WaterRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity

@Database(
    entities = [
        CategoryEntity::class,
        CommodityEntity::class,
        CommodityGrowthRecordsEntity::class,
        CommodityHealthRecordsEntity::class,
        FeedEntity::class,
        FeedingRecordsEntity::class,
        PondEntity::class,
        PondRecordsEntity::class,
        WaterRecordsEntity::class,
        PondCategoryCrossRefEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PondPediaDatabase : RoomDatabase() {
    abstract val pondsDao: PondsDao
    abstract val pondDetailsDao: PondDetailsDao
}
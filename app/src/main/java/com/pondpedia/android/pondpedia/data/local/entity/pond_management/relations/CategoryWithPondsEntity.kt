package com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.PondEntity

data class CategoryWithPondsEntity(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "pondId",
        associateBy = Junction(PondCategoryCrossRefEntity::class)
    )

    val ponds: List<PondEntity>
)

package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity

data class CategoryWithPonds(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "pondId",
        associateBy = Junction(PondCategoryCrossRefEntity::class)
    )

    val ponds: List<PondEntity>
)

package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity

data class PondWithCategories(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "categoryId",
        associateBy = Junction(PondCategoryCrossRefEntity::class)
    )

    val categories: List<CategoryEntity>
)

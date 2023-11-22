package com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.PondEntity

data class PondWithCategoriesEntity(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "categoryName",
        associateBy = Junction(PondCategoryCrossRefEntity::class)
    )

    val categories: List<CategoryEntity>
)

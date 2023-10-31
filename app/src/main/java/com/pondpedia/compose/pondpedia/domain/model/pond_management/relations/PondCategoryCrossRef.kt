package com.pondpedia.compose.pondpedia.domain.model.pond_management.relations

import androidx.room.Entity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity

@Entity(primaryKeys = ["pondId", "categoryId"])
data class PondCategoryCrossRef(
    val pondId: Long,
    val categoryId: Long
) {
    fun toPondCategoryCrossRefEntity(): PondCategoryCrossRefEntity {
        return PondCategoryCrossRefEntity(
            pondId = pondId,
        categoryId = categoryId
        )
    }
}

package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Entity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.relations.PondCategoryCrossRef

@Entity(tableName = "pond_category_cross_ref_table" ,primaryKeys = ["pondId", "categoryId"])
data class PondCategoryCrossRefEntity(
    val pondId: Long,
    val categoryId: Long
) {
    fun toPondCategoryCrossRef(): PondCategoryCrossRef {
        return PondCategoryCrossRef(
            pondId = pondId,
            categoryId = categoryId
        )
    }
}

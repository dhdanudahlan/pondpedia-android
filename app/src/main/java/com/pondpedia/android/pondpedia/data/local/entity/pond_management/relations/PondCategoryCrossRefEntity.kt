package com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Entity
import androidx.room.Index
import com.pondpedia.android.pondpedia.domain.model.pond_management.relations.PondCategoryCrossRef

@Entity(
    tableName = "pond_category_cross_ref_table",
    primaryKeys = ["pondId", "categoryName"],
    indices = [Index(value = ["categoryName"])]
)
data class PondCategoryCrossRefEntity(
    val pondId: Long,
    val categoryName: String
) {
    fun toPondCategoryCrossRef(): PondCategoryCrossRef {
        return PondCategoryCrossRef(
            pondId = pondId,
            categoryName = categoryName
        )
    }
}

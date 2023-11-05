package com.pondpedia.compose.pondpedia.domain.model.pond_management.relations

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity

data class PondCategoryCrossRef(
    val pondId: Long,
    val categoryName: String
) {
    fun toPondCategoryCrossRefEntity(): PondCategoryCrossRefEntity {
        return PondCategoryCrossRefEntity(
            pondId = pondId,
            categoryName = categoryName
        )
    }
}

package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity

data class Category(
    val categoryId: Long,

    val name: String,
) {
    fun toCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            categoryId = categoryId,
            name = name
        )
    }
}

package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity

data class Category(
    val categoryName: String,
) {
    fun toCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            categoryName = categoryName,
        )
    }
}

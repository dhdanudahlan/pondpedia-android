package com.pondpedia.android.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val categoryName: String,
) {
    fun toCategory(): Category {
        return Category(
            categoryName = categoryName,
        )
    }
}

package com.pondpedia.android.pondpedia.data.remote.dto.pond

import com.google.gson.annotations.SerializedName
import com.pondpedia.android.pondpedia.data.remote.dto.user.FarmerResponse
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import java.util.Locale

data class PondResponse(
    val id: Long,
    val user: List<FarmerResponse>,
    val name: String,
    val area: Long,
    val depth: Long,
    @field:SerializedName("pond_type")
    val pondType: String,
    @field:SerializedName("water_type")
    val waterType: String,
    val location: String,
    val description: String,
    val updatedAt: String,
    val createdAt: String,
) {
    fun toPond() = Pond(
        pondId = id,
        name = name,
        area = area.toString(),
        depth = depth.toString(),
        pondType = pondType,
        waterType = waterType,
        location = location,
        description = description,
        updatedDate = updatedAt,
        createdDate = createdAt,
    )
}

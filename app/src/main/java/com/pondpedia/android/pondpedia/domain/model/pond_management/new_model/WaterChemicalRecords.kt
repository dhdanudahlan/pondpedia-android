package com.pondpedia.android.pondpedia.domain.model.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.WaterChemicalRecordsEntity

data class WaterChemicalRecords(

    val recordId: Long = 0,

    val date: Int = 0,

    val ammonia: Float? = null, // Good: 0-0.1 | Bad: Above 0.2 | ppm
    val nitrite: Float? = null, // Good: 0-0.05 | Bad: Above 0.1 | ppm

    val nitrate: Float? = null, // Good: 5-20 | Bad: Above 50 or below 5 | ppm
    val potassium: Float? = null, // Good: 5-20 | Bad: Below 2 or above 30 | ppm

    val phosphate: Float? = null, // Good: 0.1-1 | Bad: Above 2 | ppm
    val alkalinity: Float? = null, // Good: 80-150 | Bad: Below 50 or above 200 | ppm
    val hardness: Float? = null, // Good: 50-200 | Bad: Below 50 or above 300 | ppm
    val calcium: Float? = null, // Good: 40-100 | Bad: Below 20 or above 150 | ppm
    val magnesium: Float? = null, // Good: 10-50 | Bad: Below 5 or above 100 | ppm
    val carbonate: Float? = null, // Good: 10-30 | Bad: Below 5 or above 50 | ppm
    val bicarbonate: Float? = null, // Good: 50-150 | Bad: Below 20 or above 200 | ppm
    val totalOrganicMatter: Float? = null, // Good: 0-5 | Bad: Above 10 | ppm
    val ammonium: Float? = null, // Good: 0-0.5 | Bad: Above 1 | ppm
    val totalAmmonia: Float? = null, // Good: 0-0.2 | Bad: Above 0.5 | ppm

    val note: String = "",

    val pondId: Long = 0,
) {
    fun toWaterChemicalRecordsEntity(): WaterChemicalRecordsEntity {
        return WaterChemicalRecordsEntity(
            recordId = recordId,
            date = date,
            ammonia = ammonia,
            nitrite = nitrite,
            nitrate = nitrate,
            potassium = potassium,
            phosphate = phosphate,
            alkalinity = alkalinity,
            hardness = hardness,
            calcium = calcium,
            magnesium = magnesium,
            carbonate = carbonate,
            bicarbonate = bicarbonate,
            totalOrganicMatter = totalOrganicMatter
        )
    }
}

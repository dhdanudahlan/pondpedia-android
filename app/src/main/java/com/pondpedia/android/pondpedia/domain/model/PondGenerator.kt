package com.pondpedia.android.pondpedia.domain.model

import com.pondpedia.android.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.Feed
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

object PondGenerator {
    fun GeneratePond(): Pond {
        val uuid = UUID.randomUUID()
        return Pond(
            pondId = 0,
            name = "Pond-$uuid",
            area = (10..5000).random().toString(),
            depth = (1..10).random().toString(0),
            pondType = "HDPE",
            waterType = "Payau",
            location ="Malang",
            description = "Kolam Dummy",
            farmerId = (0).toString(),
        )
    }
    fun GenerateCommodity(pond: Pond): Commodity {
        val uuid = UUID.randomUUID()
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()
        return Commodity(
            commodityId = 0,
            date = currentDate,
            origin = "Pembibitan $uuid",
            quantity = (500..500000).random().toString(),
            name = "Udang Vaname",
            scientificName = "Litopenaeus vannamei",
            pondId = pond.pondId,
        )
    }
    fun GenerateFeed(): Feed {
        val uuid = UUID.randomUUID()
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()
        return Feed(
            feedId = 0,
            date = currentDate,
            origin = "PT Sigma",
            name = "A Qualitee",
            nutritionalValue = "{}"
        )
    }

    fun GeneratePondRecords(pond: Pond): PondRecords {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()
        return PondRecords(
            recordId = 0,
            date = currentDate,
            cycle = (0 .. 10).random().toString(),
            note = "Data dummy kolam",
            pondId = pond.pondId
        )
    }

    fun GenerateWaterRecords(pond: Pond): WaterRecords {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()

        val pH = ((6 .. 8).random().toFloat() + ((0 .. 99).random().toFloat() / 100.0))
        val dissolvedOxygen = ((5 .. 8).random().toFloat() + ((0 .. 99).random().toFloat() / 100.0))
        val temperature = (25 .. 35).random().toDouble()
        val salinity = (500 .. 3500).random().toDouble()
        val turbidity = (80 .. 120).random().toDouble()
        val clarity = (80 .. 120).random().toDouble()

        return WaterRecords(
            recordId = 0,
            date = currentDate,
            waterHeight = (1 .. 100).random(),
            weather = "Cerah",
            color = "Coklat",
            pH = pH,
            temperature = temperature,
            dissolvedOxygen = dissolvedOxygen,
            salinity = salinity,
            turbidity = turbidity,
            ammonia = (0 .. 100).random().toDouble(),
            nitrite = (0 .. 100).random().toDouble(),
            alkalinity = (0 .. 100).random().toDouble(),
            note = "Data dummy air",
            pondId = pond.pondId,
        )
    }
    fun GenerateFeedingRecords(pond: Pond, feed: Feed = Feed(0, LocalDateTime.now().toString(), "Unspecified", "Unspecified", "Unspecified")): FeedingRecords {

        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()

        return FeedingRecords(
            recordId = 0,
            date = currentDate,
            quantity = ((1 .. 1000).random().toFloat() + ((0 .. 99).random().toFloat() / 100)),
            note = "Data dummy pakan",
            feedId = feed.feedId,
            pondId = pond.pondId
        )
    }

    fun generateCommodityGrowthRecords(commodity: Commodity, age: Int = 1): CommodityGrowthRecords {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()
        return CommodityGrowthRecords(
            recordId = 0,
            date = currentDate,
            age = age,
            length = (1 .. 300).random(),
            weight = (1 .. 5000).random(),
            note = "Data dummy pertumbuhan komoditas",
            commodityId = commodity.commodityId
        )
    }
    fun generateCommodityHealthRecords(commodity: Commodity, death: Int = 0): CommodityHealthRecords {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")
        val currentDate = LocalDateTime.now().toString()
        return CommodityHealthRecords(
            recordId = 0,
            date = currentDate,
            death = death,
            indicator = "Data dummy indikator kesehatan komoditas",
            action = "Data dummy aksi kesehatan komoditas",
            note = "Data dummy kesehatan komoditas",
            commodityId = commodity.commodityId
        )
    }
}
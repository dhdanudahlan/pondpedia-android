package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithHealthRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.FeedingRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.WaterRecords


data class PondDetailsState(
    val pond: Pond = Pond(),

    val pondRecords: List<PondRecords> = emptyList(),
    val waterRecords: List<WaterRecords> = emptyList(),
    val feedingRecords: List<FeedingRecords> = emptyList(),

    val commodity: List<Commodity> = emptyList(),
    val commodityGrowthRecords: List<CommodityWithGrowthRecords> = emptyList(),
    val commodityHealthRecords: List<CommodityWithHealthRecords> = emptyList(),

    val pondId: Long = 0,

    val commodityId: Long = 0,

    val name: String = "",

    val area: String = "",

    val depth: String = "",

    val pondType: String = "",

    val waterType: String = "",

    val location: String = "",

    val description: String = "",


    val commodityDate: String = "",

    val commodityOrigin: String = "",

    val commodityEntity: String = "",

    val commodityName: String = "",

    val commoditySciName: String = "",


    val commodityGrowthRecordsDate: String = "",

    val commodityGrowthRecordsAge: String = "",

    val commodityGrowthRecordsLength: String = "",

    val commodityGrowthRecordsWeight: String = "",

    val commodityGrowthRecordsNote: String = "",


    val commodityHealthRecordsDate: String = "",

    val commodityHealthRecordsDeath: String = "",

    val commodityHealthRecordsIndicator: String = "",

    val commodityHealthRecordsAction: String = "",

    val commodityHealthRecordsNote: String = "",


    val pondRecordsDate: String = "",

    val pondRecordsCycle: String = "",

    val pondRecordsNote: String = "",


    val waterRecordsDate: String = "",

    val waterRecordsLevel: String = "",

    val waterRecordsQuality: String = "",

    val waterRecordsColor: String = "",

    val waterRecordsNote: String = "",


    val feedingRecordsDate: String = "",

    val feedingRecordsQuantity: String = "",

    val feedingRecordsNote: String = "",


    val pondCategoryCrossRefCategoryName: String = "",


    val feedDate: String = "",

    val feedOrigin: String = "",

    val feedName: String = "",

    )
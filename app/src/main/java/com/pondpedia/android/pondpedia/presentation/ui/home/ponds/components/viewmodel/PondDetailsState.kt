package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel

import com.pondpedia.android.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords


data class PondDetailsState(

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = "",

    val pond: Pond = Pond(),

    val pondRecords: List<PondRecords> = emptyList(),
    val waterRecords: List<WaterRecords> = emptyList(),
    val feedingRecords: List<FeedingRecords> = emptyList(),

    val commodity: List<Commodity> = emptyList(),
    val commodityGrowthRecords: List<CommodityGrowthRecords> = emptyList(),
    val commodityHealthRecords: List<CommodityHealthRecords> = emptyList(),

    val isAddingCommodity: Boolean = false,

    val pondId: Long = 0,

    val commodityId: Long = 0,

    val feedId: Long = 0,

    val name: String = "",

    val area: String = "",

    val depth: String = "",

    val pondType: String = "",

    val waterType: String = "",

    val location: String = "",

    val description: String = "",


    val commodityDate: String = "",

    val commodityOrigin: String = "",

    val commodityQuantity: String = "",

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

    val waterRecordsColor: String = "",

    val waterRecordsPH: String = "",

    val waterRecordsTemperature: String = "",

    val waterRecordsWeather: String = "",

    val waterRecordsDissolvedOxygen: String = "",

    val waterRecordsSalinity: String = "",

    val waterRecordsTurbidity: String = "",

    val waterRecordsAlkalinity: String = "",

    val waterRecordsNote: String = "",


    val feedingRecordsDate: String = "",

    val feedingRecordsQuantity: String = "",

    val feedingRecordsNote: String = "",


    val pondCategoryCrossRefCategoryName: String = "",


    val feedDate: String = "",

    val feedOrigin: String = "",

    val feedName: String = "",

    )
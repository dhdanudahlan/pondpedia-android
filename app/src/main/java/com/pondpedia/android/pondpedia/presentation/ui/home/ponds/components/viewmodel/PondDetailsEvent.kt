package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel

sealed interface PondDetailsEvent{
    data object DeletePond: PondDetailsEvent
    data object EditPond: PondDetailsEvent
    
    data object AddCommodity: PondDetailsEvent
    data object AddCommodityGrowthRecords: PondDetailsEvent
    data object AddCommodityHeathRecords: PondDetailsEvent
    data object AddPondRecords: PondDetailsEvent
    data object AddWaterRecords: PondDetailsEvent
    data object AddFeedingRecords: PondDetailsEvent
    data object AddPondCategoryCrossRef: PondDetailsEvent
    data object AddFeed: PondDetailsEvent

    data class SetPondId(val pondId: Long): PondDetailsEvent
    data class SetCommodityId(val commodityId: Long): PondDetailsEvent

    data class SetCommodityDate(val commodityDate: String): PondDetailsEvent
    data class SetCommodityOrigin(val commodityOrigin: String): PondDetailsEvent
    data class SetCommodityQuantity(val commodityQuantity: String): PondDetailsEvent
    data class SetCommodityEntity(val commodityEntity: String): PondDetailsEvent
    data class SetCommodityName(val commodityName: String): PondDetailsEvent
    data class SetCommoditySciName(val commoditySciName: String): PondDetailsEvent
    
    data class SetCommodityGrowthRecordsDate(val commodityGrowthRecordsDate: String):
        PondDetailsEvent
    data class SetCommodityGrowthRecordsAge(val commodityGrowthRecordsAge: String): PondDetailsEvent
    data class SetCommodityGrowthRecordsLength(val commodityGrowthRecordsLength: String):
        PondDetailsEvent
    data class SetCommodityGrowthRecordsWeight(val commodityGrowthRecordsWeight: String):
        PondDetailsEvent
    data class SetCommodityGrowthRecordsNote(val commodityGrowthRecordsNote: String):
        PondDetailsEvent

    data class SetCommodityHealthRecordsDate(val commodityHealthRecordsDate: String):
        PondDetailsEvent
    data class SetCommodityHealthRecordsDeath(val commodityHealthRecordsDeath: String):
        PondDetailsEvent
    data class SetCommodityHealthRecordsIndicator(val commodityHealthRecordsIndicator: String):
        PondDetailsEvent
    data class SetCommodityHealthRecordsAction(val commodityHealthRecordsAction: String):
        PondDetailsEvent
    data class SetCommodityHealthRecordsNote(val commodityHealthRecordsNote: String):
        PondDetailsEvent

    data class SetPondRecordsDate(val pondRecordsDate: String): PondDetailsEvent
    data class SetPondRecordsCycle(val pondRecordsCycle: String): PondDetailsEvent
    data class SetPondRecordsNote(val pondRecordsNote: String): PondDetailsEvent

    data class SetWaterRecordsDate(val value: String): PondDetailsEvent
    data class SetWaterRecordsLevel(val value: String): PondDetailsEvent
    data class SetWaterRecordsPH(val value: String): PondDetailsEvent
    data class SetWaterRecordsTemperature(val value: String): PondDetailsEvent
    data class SetWaterRecordsWeather(val value: String): PondDetailsEvent
    data class SetWaterRecordsDissolvedOxygen(val value: String): PondDetailsEvent
    data class SetWaterRecordsSalinity(val value: String): PondDetailsEvent
    data class SetWaterRecordsTurbidity(val value: String): PondDetailsEvent
    data class SetWaterRecordsClarity(val value: String): PondDetailsEvent
    data class SetWaterRecordsColor(val value: String): PondDetailsEvent
    data class SetWaterRecordsNote(val value: String): PondDetailsEvent

    data class SetFeedingRecordsDate(val feedingRecordsDate: String): PondDetailsEvent
    data class SetFeedingRecordsQuantity(val feedingRecordsQuantity: String): PondDetailsEvent
    data class SetFeedingRecordsNote(val feedingRecordsNote: String): PondDetailsEvent
    
    data class SetPondCategoryCrossRefCategoryName(val pondCategoryCrossRefCategoryName: String):
        PondDetailsEvent
    
    data class SetFeedDate(val feedDate: String): PondDetailsEvent
    data class SetFeedOrigin(val feedOrigin: String): PondDetailsEvent
    data class SetFeedName(val feedName: String): PondDetailsEvent


    data object ShowDialog: PondDetailsEvent
    data object HideDialog: PondDetailsEvent

    data object DismissCommonDialog: PondDetailsEvent
    data object DismissCommonDialogWithClearState: PondDetailsEvent
}
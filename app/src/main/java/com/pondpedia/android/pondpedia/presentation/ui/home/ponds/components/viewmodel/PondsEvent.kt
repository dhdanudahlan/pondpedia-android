package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel

sealed interface PondsEvent{
    data object AddPond: PondsEvent
    data class DeletePond(val selectedPondId: Long): PondsEvent

    data class SetName(val name: String): PondsEvent

    data class SetArea(val area: String): PondsEvent

    data class SetDepth(val depth: String): PondsEvent

    data class SetPondType(val pondType: String): PondsEvent

    data class SetWaterType(val waterType: String): PondsEvent

    data class SetLocation(val location: String): PondsEvent

    data class SetDescription(val description: String): PondsEvent

    data class SetFarmerId(val farmerId: Long): PondsEvent
    data object ShowDialog: PondsEvent
    data object HideDialog: PondsEvent
    data class SelectCategory(val selectedCategory: Int): PondsEvent
    data class SelectPond(val selectedPondId: Long): PondsEvent
    data class SortPonds(val sortType: SortType): PondsEvent

}
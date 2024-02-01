package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel

import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import java.time.LocalDateTime

data class PondsState(

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,

    val errorMessage: String = "",

    val categories: List<Category> = emptyList(),

    val ponds: List<Pond> = emptyList(),

    val pond: Pond = Pond(),

    val name: String = "",

    val area: String = "",

    val depth: String = "",

    val pondType: String = "",

    val waterType: String = "",

    val location: String = "",

    val description: String = "",

    val createdDate: String = DateGenerator.getCurrentDateTime(),

    val updatedDate: String = DateGenerator.getCurrentDateTime(),

    val farmerId: Long = 0,

    val isAddingPond: Boolean = false,

    val selectedCategoryIndex: Int = 0,

    val selectedPondId: Long = 0,

    val sortType: SortType = SortType.CREATED_DATE
)

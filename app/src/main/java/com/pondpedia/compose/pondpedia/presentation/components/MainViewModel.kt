package com.pondpedia.compose.pondpedia.presentation.components
//
//import android.util.Log
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import com.pondpedia.compose.pondpedia.domain.use_case.ponds.GetCategoryListUseCase
//import com.pondpedia.compose.pondpedia.presentation.screens.home.components.TopActionBarItem
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
////@HiltViewModel
////class MainViewModel @Inject constructor(
////    private val getPondCategoriesUseCase: GetCategoryListUseCase,
////) : ViewModel() {
////    private val _state = mutableStateOf(MainState())
////    val state: State<MainState> = _state
////
////    fun setSelectedNavItem(navItem: BottomNavItem) {
////        _state.value = _state.value.copy(selectedNavItem = navItem)
////        when(navItem) {
////            BottomNavItem.PONDS -> setTopActionBarItem(TopActionBarItem.PONDS)
////            BottomNavItem.UPDATES -> setTopActionBarItem(TopActionBarItem.UPDATES)
////            BottomNavItem.MENU -> setTopActionBarItem(TopActionBarItem.MENU)
////            BottomNavItem.EXPLORE -> setTopActionBarItem(TopActionBarItem.EXPLORE)
////            BottomNavItem.MORE -> setTopActionBarItem(TopActionBarItem.MORE)
////        }
////    }
////    fun setTopActionBarItem(topActionBarItem: TopActionBarItem) {
////        Log.d("MainViewModel", "topActionBarItem Title -> ${topActionBarItem.title_short}")
////        _state.value = _state.value.copy(topActionBarItem = topActionBarItem)
////    }
////
////    fun setCurrentRoute(route: String) {
////        _state.value = _state.value.copy(currentRoute = route)
////        when(route) {
////            "home" -> setSelectedNavItem(BottomNavItem.PONDS)
////            "details" -> setTopActionBarItem(TopActionBarItem.DETAILS)
////            "create" -> setTopActionBarItem(TopActionBarItem.CREATE)
////            "update" -> setTopActionBarItem(TopActionBarItem.UPDATE)
////        }
////    }
////
////    fun setDisplayActionMenu(displayed: Boolean) {
////        _state.value = _state.value.copy(displayActionMenu = displayed)
////    }
////    fun setDisplayActionScreen(displayed: Boolean) {
////        _state.value = _state.value.copy(displayActionScreen = displayed)
////    }
////
////}
////
////data class MainState(
////    val selectedNavItem: BottomNavItem = BottomNavItem.PONDS,
////    val topActionBarItem: TopActionBarItem = TopActionBarItem.PONDS,
////    val currentRoute: String = "home",
////
////    val displayActionMenu: Boolean = false,
////    val displayActionScreen: Boolean = false,
////)
//
//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val getPondCategoriesUseCase: GetCategoryListUseCase,
//) : ViewModel() {
//    private val _state = mutableStateOf(MainState())
//    val state: State<MainState> = _state
//
//    fun setTopActionBarItem(topActionBarItem: TopActionBarItem) {
//        Log.d("MainViewModel", "topActionBarItem Title -> ${topActionBarItem.title_short}")
//        _state.value = _state.value.copy(topActionBarItem = topActionBarItem)
//    }
//
//    fun setCurrentRoute(route: String) {
//        _state.value = _state.value.copy(currentRoute = route)
////        when(route) {
////            "home" -> setSelectedNavItem(BottomNavItem.PONDS)
////            "details" -> setTopActionBarItem(TopActionBarItem.DETAILS)
////            "create" -> setTopActionBarItem(TopActionBarItem.CREATE)
////            "update" -> setTopActionBarItem(TopActionBarItem.UPDATE)
////        }
//    }
//
//    fun setDisplayActionMenu(displayed: Boolean) {
//        _state.value = _state.value.copy(displayActionMenu = displayed)
//    }
//    fun setDisplayActionScreen(displayed: Boolean) {
//        _state.value = _state.value.copy(displayActionScreen = displayed)
//    }
//
//}
//
//data class MainState(
//
//    val displayActionMenu: Boolean = false,
//    val displayActionScreen: Boolean = false,
//)
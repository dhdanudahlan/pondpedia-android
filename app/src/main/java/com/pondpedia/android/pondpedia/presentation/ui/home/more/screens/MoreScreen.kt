package com.pondpedia.android.pondpedia.presentation.ui.home.more.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.presentation.ui.home.more.components.MoreScreens

@Composable
fun MoreScreen(
    onRouteChanged: (String) -> Unit,
) {
    val items = listOf(
        MoreScreens.Profile,
        MoreScreens.Settings
    )
    val itemCategories = items.map { it.categoryId }.distinct()
    Spacer(modifier = Modifier.height(8.dp))
    CategorizedLazyColumn(
        categories = itemCategories,
        items = items,
        onRouteChanged = onRouteChanged
    )
    
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    categories: List<Int>,
    items: List<MoreScreens>,
    modifier: Modifier = Modifier,
    onRouteChanged: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            stickyHeader {
                Divider()
            }

            val itemsCategorized = items.filter { it.categoryId == category }
            items( itemsCategorized ) {item ->
                CategoryItem(
                    text = stringResource(item.labelTextId),
                    route = item.route,
                    onClickItem = onRouteChanged,
                    modifier = modifier
                )
            }

        }
    }
}

@Composable
fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    /*Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )*/
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}
@Composable
fun CategoryItem(
    text: String,
    route: String,
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .clickable { onClickItem(route) }
    )
}

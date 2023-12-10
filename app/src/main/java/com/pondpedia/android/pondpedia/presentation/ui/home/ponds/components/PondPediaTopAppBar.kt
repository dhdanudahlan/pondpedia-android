package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PondPediaTopAppBar(
    @StringRes titleRes: Int,
    actionReturnIcon: ImageVector,
    actionReturnIconContentDescription: String?,
    actionOptionsIcon: ImageVector,
    actionOptionsIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionReturnClick: () -> Unit = {},
    onActionOptionsClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onActionReturnClick) {
                Icon(
                    imageVector = actionReturnIcon,
                    contentDescription = actionReturnIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionOptionsClick) {
                Icon(
                    imageVector = actionOptionsIcon,
                    contentDescription = actionOptionsIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("pondpediaTopAppBar"),
    )
}
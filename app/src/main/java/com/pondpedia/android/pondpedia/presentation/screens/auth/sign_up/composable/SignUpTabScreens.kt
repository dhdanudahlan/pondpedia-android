package com.pondpedia.android.pondpedia.presentation.screens.auth.sign_up.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.screens.auth.sign_up.data.Occupation

@Composable
fun OccupationTabScreen(
    onClick: (String) -> Unit
) {

    val occupations = listOf(
        Occupation(
            category = stringResource(R.string.occupation_type_farmer),
            occupations = listOf(
                stringResource(R.string.occupation_type_farmer_1),
                stringResource(R.string.occupation_type_farmer_2),
                stringResource(R.string.occupation_type_farmer_3),
                stringResource(R.string.occupation_type_farmer_4),
                stringResource(R.string.occupation_type_farmer_5),
                stringResource(R.string.occupation_type_farmer_6),
                stringResource(R.string.occupation_type_farmer_7),
            )
        ),
        Occupation(
            category = stringResource(R.string.occupation_type_non_farmer),
            occupations = listOf(
                stringResource(R.string.occupation_type_non_farmer_1),
                stringResource(R.string.occupation_type_non_farmer_2),
                stringResource(R.string.occupation_type_non_farmer_3),
                stringResource(R.string.occupation_type_non_farmer_4),
                stringResource(R.string.occupation_type_non_farmer_5),
                stringResource(R.string.occupation_type_non_farmer_6),
                stringResource(R.string.occupation_type_non_farmer_7),
                stringResource(R.string.occupation_type_non_farmer_8),
            )
        )
    )

    var selectedTab by rememberSaveable { mutableStateOf(occupations[0].category) }
    var tabIndex by rememberSaveable { mutableStateOf(0) }


    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                occupations.forEachIndexed { index, occupation ->
                    Tab(
                        text = { Text(text = occupation.category, fontSize = 12.sp, maxLines = 1) },
                        selected = selectedTab == occupation.category,
                        onClick = {
                            selectedTab = occupation.category
                            tabIndex = index
                        }
                    )
                }
            }
            when (selectedTab) {
                occupations[0].category -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Divider(modifier = Modifier.height(2.dp), thickness = 1.dp)
                        occupations[0].occupations.forEachIndexed { _, occupationName ->
                            TextButton(
                                onClick = { onClick(occupationName) },
                                modifier = Modifier
                            ) {
                                Text(text = occupationName)
                            }
                            Divider(modifier = Modifier.height(2.dp), thickness = 1.dp)
                        }
                    }
                }
                occupations[1].category -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Divider(modifier = Modifier.height(2.dp), thickness = 1.dp)
                        occupations[1].occupations.forEachIndexed { _, occupationName ->
                            TextButton(
                                onClick = { onClick(occupationName) },
                                modifier = Modifier
                            ) {
                                Text(text = occupationName)
                            }
                            Divider(modifier = Modifier.height(2.dp), thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun InformationSourceTabScreen(
    onClick: (String) -> Unit
) {

    val informationSources = listOf(
        stringResource(R.string.information_source_1),
        stringResource(R.string.information_source_2),
        stringResource(R.string.information_source_3),
        stringResource(R.string.information_source_4),
        stringResource(R.string.information_source_5),
        stringResource(R.string.information_source_6),
        stringResource(R.string.information_source_7),
        stringResource(R.string.information_source_8),
        stringResource(R.string.information_source_9),
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Divider(modifier = Modifier.height(2.dp), thickness = 1.dp)
            informationSources.forEachIndexed { _, source ->
                TextButton(
                    onClick = { onClick(source) },
                    modifier = Modifier
                ) {
                    Text(text = source)
                }
                Divider(modifier = Modifier.height(2.dp), thickness = 1.dp)
            }
        }
    }
}
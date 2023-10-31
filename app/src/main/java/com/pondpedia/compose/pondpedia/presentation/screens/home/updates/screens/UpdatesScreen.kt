package com.pondpedia.compose.pondpedia.presentation.screens.home.updates.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.presentation_copy.ui.theme.PondPediaCustomTheme

@Preview
@Composable
fun PondScreenPreview() {
    PondPediaCustomTheme(darkTheme = true) {
        com.pondpedia.compose.pondpedia.presentation_copy.screens.home.updates.screens.UpdatesScreen()
    }
}


@Composable
fun UpdatesScreen(
    tabs: List<String> = listOf(
        "Semua",
        "Kolam",
        "Lainnya"
    )
) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(tabs[0]) }
    val (tabIndex, setTabIndex) = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        com.pondpedia.compose.pondpedia.presentation_copy.screens.home.updates.screens.UpdatesTabs(
            tabs,
            selectedTab,
            tabIndex,
            setSelectedTab,
            setTabIndex
        )
        com.pondpedia.compose.pondpedia.presentation_copy.screens.home.updates.screens.UpdatesLazyList(
            tabs,
            selectedTab
        )
    }
}

@Composable
fun UpdatesTabs(
    tabs: List<String>,
    selectedTab: String,
    tabIndex: Int,
    onTabSelected: (String) -> Unit,
    onTabIndexSelected: (Int) -> Unit
) {
    TabRow(selectedTabIndex = tabIndex) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(text = tab, fontSize = 12.sp, maxLines = 1)
                },
                selected = selectedTab == tab,
                onClick = {
                    onTabSelected(tab)
                    onTabIndexSelected(index)
                },
            )
        }
    }
}

@Composable
fun UpdatesLazyList(
    tabs: List<String>,
    selectedTab: String,
) {
    when(selectedTab) {
        tabs[0] -> {
            Text(text = tabs[0])
        }
        tabs[1] -> {
            Text(text = tabs[1])
        }
        tabs[2] -> {
            Text(text = tabs[2])
        }
        tabs[3] -> {
            Text(text = tabs[3])
        }
    }
}

@Composable
fun UpdatesLazyListContent(
) {

}
@Composable
fun HistoryScreenA() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Kolam", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pond 1.3.7", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Kolam berhasil dibuat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Kolam", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pond 1.3.2", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Kolam berhasil dibuat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Explore", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Informasi Baru", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Tips menentukan jenis pakan yang tepat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Kolam", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pond 1.2.2", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Kolam berhasil dibuat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Lainnya", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Versi Baru Tersedia", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Update aplikasi ke versi terbaru demi kemanan",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryScreenB() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Kolam", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pond 1.3.7", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Kolam berhasil dibuat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Kolam", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pond 1.3.2", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Kolam berhasil dibuat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{}
        ) {
            Row {
                Box {
                    Text(text = "Kolam", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pond 1.2.2", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Kolam berhasil dibuat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryScreenC() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {}
        ) {
            Row {
                Box {
                    Text(text = "Pedia", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Informasi Baru", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Tips menentukan jenis pakan yang tepat",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryScreenD() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {}
        ) {
            Row {
                Box {
                    Text(text = "Lainnya", fontWeight = FontWeight.Bold, maxLines = 1)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Versi Baru Tersedia", fontWeight = FontWeight.SemiBold, maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Versi 1.2 | Segera update aplikasi untuk mendapatkan keamanan dan kenyamanan maksimal",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
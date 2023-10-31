package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.create.screens
//import com.pondpedia.compose.pondpedia.presentation.components.MainState
//import com.pondpedia.compose.pondpedia.presentation.screens.home.components.TopActionBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondDummyGenerator
import com.pondpedia.compose.pondpedia.presentation_copy.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.PondPediaTopAppBar
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation_copy.ui.theme.PondPediaCustomTheme

@Preview(showBackground = true)
@Composable
fun CreateScreenPreview() {
    PondPediaCustomTheme(darkTheme = false) {
        com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreateScreen(
            homeState = PondPediaAppState(
                navController = rememberNavController(),
                coroutineScope = rememberCoroutineScope()
            ),
            pondsState = PondsState(
                pondLogList = PondDummyGenerator.getDummyPondLogList(5)
            ),
            pondState = PondState(
                pondCreateTabList = listOf("Kolam", "Ikan", "Air", "Pakan"),
            ),
            onTabIndexSelected = {},
        ) {}
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    pondState: PondState,
    onTabIndexSelected: (Int) -> Unit,
    onPondSaved: (String) -> Unit
) {


    val navController = homeState.navController

    val snackbarHostState = remember { SnackbarHostState() }

    var showSettingsMenu by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold { innerPadding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val currentDestination = homeState.currentPondScreenDestination
                if (currentDestination != null) {
                    PondPediaTopAppBar(
                        titleRes = currentDestination.titleTextId,
                        actionReturnIcon = Icons.Default.ArrowBack,
                        actionReturnIconContentDescription = stringResource(
                            id = currentDestination.titleTextId,
                        ),
                        actionOptionsIcon = Icons.Default.MoreVert,
                        actionOptionsIconContentDescription = stringResource(
                            id = currentDestination.titleTextId,
                        ),
                        onActionReturnClick =  { navController.popBackStack() },
                        onActionOptionsClick = { showSettingsMenu = !showSettingsMenu }
                    )
                }
                com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreateTabs(
                    pondState = pondState,
                    onTabIndexSelected = onTabIndexSelected,
                )
                com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreatePondContent(
                    pondState = pondState,
                    paddingValues = innerPadding
                )
            }
        }
    }
}

@Composable
fun CreateTabs(
    pondState: PondState,
    onTabIndexSelected: (Int) -> Unit,
) {
    val selectedTabIndex = remember(pondState.selectedTabIndex) { pondState.selectedTabIndex }

    TabRow(selectedTabIndex = selectedTabIndex) {
        pondState.pondCreateTabList.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(text = tab, fontSize = 12.sp, maxLines = 1)
                },
                selected = tab == pondState.pondCreateTabList[selectedTabIndex],
                onClick = { onTabIndexSelected(index)}
            )
        }
    }
}

@Composable
fun CreatePondContent(
    pondState: PondState,
    paddingValues: PaddingValues
) {

    val selectedTabIndex = remember(pondState.selectedTabIndex) { pondState.selectedTabIndex }

    Box(
        modifier = Modifier
            .padding(PaddingValues(horizontal = 4.dp, vertical = 1.dp))
    ) {
        when (selectedTabIndex) {
            0 -> com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreatePondContentPond(
                pondState = pondState,
                paddingValues = paddingValues
            )
            1 -> com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreatePondContentFish(
                pondState = pondState,
                paddingValues = paddingValues
            )
            2 -> com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreatePondContentWater(
                pondState = pondState,
                paddingValues = paddingValues
            )
            3 -> com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreatePondContentFeed(
                pondState = pondState,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun CreatePondContentPond(
    pondState: PondState,
    paddingValues: PaddingValues
) {
    var pondName by rememberSaveable { mutableStateOf( pondState.pondName ) }
    var pondLength by rememberSaveable { mutableStateOf(pondState.pondLength) }
    var pondWidth by rememberSaveable { mutableStateOf(pondState.pondWidth) }
    var pondDepth by rememberSaveable { mutableStateOf(pondState.pondDepth) }
    var pondImageUrl by rememberSaveable { mutableStateOf(pondState.pondImageUrl) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {

        Spacer(modifier = Modifier.height(8.dp))

        com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.OutlinedTextFieldCard(
            textValue = pondName,
            onValueChange = { pondState.pondName = it })

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = "",
                onValueChange = {
                    pondState.pondLength =  it.toFloat()
                },
                label = { Text("Panjang Kolam") },
                placeholder = { Text("${pondState.pondLength}") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun CreatePondContentFish(
    pondState: PondState,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {

        Spacer(modifier = Modifier.height(8.dp))
    }
}
@Composable
fun CreatePondContentWater(
    pondState: PondState,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {


            Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun CreatePondContentFeed(
    pondState: PondState,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = pondState.feedName,
                onValueChange = {
                    pondState.feedName =  it
                },
                label = { Text("Nama Pakan") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun OutlinedTextFieldCard(
    modifier: Modifier = Modifier,
    textValue: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = textValue,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            isError = isError,
            textStyle = textStyle,
            label = { if (label != null){ Text( text = label ) } },
            placeholder = { if (placeholder != null){ Text( text = placeholder ) } },
            leadingIcon = { if (leadingIcon != null) { Icon(imageVector = leadingIcon, contentDescription = label) } },
            trailingIcon = { if (trailingIcon != null) { Icon(imageVector = trailingIcon, contentDescription = label) } },
            prefix = { if (prefix != null ){ Text( text = prefix ) } },
            suffix = { if (suffix != null ){ Text( text = suffix ) } },
            supportingText = { if ( supportingText != null ) { Text( text = supportingText, color = if(isError) Color.Red else MaterialTheme.colorScheme.onBackground ) } },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
        )
    }
}
package com.pondpedia.android.pondpedia.presentation.screens.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.ui.theme.PondPediaCustomTheme

@Composable
fun AuthScreen(
    navigateToSignInScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    onGuestSignIn: () -> Unit
) {
    PondPediaCustomTheme (darkTheme = false) {
        AuthScreenLightMode(
            navigateToSignInScreen = navigateToSignInScreen,
            navigateToSignUpScreen = navigateToSignUpScreen,
            onGuestSignIn = onGuestSignIn
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenLightMode(
    navigateToSignInScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    onGuestSignIn: () -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.pondpedia_logo),
                            contentDescription = stringResource(R.string.app_name),
                        )
                    }
                },
                actions = {

                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AuthSlideScreen()
                }

//                Spacer(modifier = Modifier.height(12.dp))
//                Spacer(modifier = Modifier.weight(1f))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = navigateToSignInScreen,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = stringResource(id = R.string.signin))
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        ElevatedButton(
                            onClick = navigateToSignUpScreen,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = stringResource(id = R.string.signup))
                        }

                        TextButton(
                            onClick = onGuestSignIn,
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                        ) {
                            Text(
                                text = stringResource(id = R.string.signin_guest),
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthSlideScreen() {
    val tabItems = listOf(
        AuthSlideScreens.SlideAboutApp,
        AuthSlideScreens.SlideOfflineMode,
        AuthSlideScreens.SlidePondManagement,
        AuthSlideScreens.SlideAiChat,
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState(
        pageCount = { tabItems.size }
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HorizontalPager(
                    state = pagerState,
                    key = { tabItems[it].imageId },
                    pageSize = PageSize.Fill
                ) { index ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(tabItems[index].imageId),
                                contentDescription = stringResource(tabItems[index].titleTextId),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(tabItems[index].titleTextId),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = stringResource(tabItems[index].contentTextId),
                                    fontSize = 15.sp,
                                    maxLines = 2,
                                    minLines = 2,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                containerColor = Color.White,
                contentColor = Color(0xFF362C28),
                divider = {
                    Divider(modifier = Modifier.width(1.dp))
                }
            ) {
                tabItems.forEachIndexed { index, authSlideScreens ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        modifier = Modifier.size(4.dp)
                    )
                }
            }
        }
    }
}

sealed class AuthSlideScreens(
    val titleTextId: Int,
    val contentTextId: Int,
    val imageId: Int,
) {
    data object SlideAboutApp : AuthSlideScreens(
        titleTextId = R.string.auth_slide_title_1,
        contentTextId = R.string.auth_slide_content_1,
        imageId = R.drawable.pondpedia_logo,
    )
    data object SlideOfflineMode : AuthSlideScreens(
        titleTextId = R.string.auth_slide_title_2,
        contentTextId = R.string.auth_slide_content_2,
        imageId = R.drawable.pond_image_1,
    )
    data object SlidePondManagement : AuthSlideScreens(
        titleTextId = R.string.auth_slide_title_3,
        contentTextId = R.string.auth_slide_content_3,
        imageId = R.drawable.pondpedia_1,
    )
    data object SlideAiChat : AuthSlideScreens(
        titleTextId = R.string.auth_slide_title_4,
        contentTextId = R.string.auth_slide_content_4,
        imageId = R.drawable.brush,
    )
}

/*@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    oneTapSign: Job,
    navigateToProfileScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            AuthTopBar()
        },
        content = { padding ->
            AuthContent(
                padding = padding,
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                }
            )
        }
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToProfileScreen()
            }
        }
    )
}*/

@Preview
@Composable
fun AuthScreenPreview() {
        AuthScreen(
            navigateToSignInScreen = {},
            navigateToSignUpScreen = {},
            onGuestSignIn = {}
        )

}

@Preview(showBackground = true)
@Composable
fun AuthSlideScreenPreview() {
    AuthSlideScreen()
}
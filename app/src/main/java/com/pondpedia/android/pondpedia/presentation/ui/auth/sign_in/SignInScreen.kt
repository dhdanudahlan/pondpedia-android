@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.components.SmallSpacer
import com.pondpedia.android.pondpedia.core.util.Utils.Companion.showMessage
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.MyEmailTextField
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.MyPasswordTextField
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.OneTapSignIn
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.SignInWithGoogle
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel.AuthViewModel
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.composable.SignIn
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.viewmodel.SignInViewModel

@Composable
fun SignInScreen(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {

    PondPediaCustomTheme (darkTheme = false) {
        SignInScreenLightMode(
            state = state,
            navigateToAuthScreen = navigateToAuthScreen,
            navigateToSignUpScreen = navigateToSignUpScreen,
            navigateToHomeScreen = navigateToHomeScreen,
            viewModel = viewModel
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreenLightMode(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    val keyboard = LocalSoftwareKeyboardController.current

    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.email
                )
            )
        }
    )
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.password
                )
            )
        }
    )



    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_signin_page))
                },
                navigationIcon = {
                    IconButton(onClick = navigateToAuthScreen) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.pondpedia_logo),
                            contentDescription = "Brush",
                            modifier = Modifier
                                .size(160.dp)
                                .alpha(0.75f),
                        )
                        Text(
                            text = "PondPedia",
                            color = Color( red = 173, green = 216, blue = 230),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Spacer(modifier = Modifier.weight(1f))
                MyEmailTextField(
                    email = email,
                    onEmailValueChange = { newValue ->
                        email = newValue
                    }
                )
                SmallSpacer()
                MyPasswordTextField(
                    password = password,
                    onPasswordValueChange = { newValue ->
                        password = newValue
                    }
                )
                SmallSpacer()

                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                keyboard?.hide()
                                viewModel.signInWithEmailAndPassword(email.text, password.text)
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.signin),
                                maxLines = 1
                            )
                        }

                        Spacer(modifier = Modifier.height(3.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.Gray)
//                                    .alpha(0.01f)
                            )
                            Text(
                                text = stringResource(R.string.sign_in_with),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(3.dp))


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            onClick = {  }
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.ic_facebook_logo
                                    ),
                                    contentDescription = stringResource(
                                        id = R.string.signin_facebook
                                    ),
                                    modifier = Modifier.height(24.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(id = R.string.facebook),
                                    maxLines = 1
                                )
                            }

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onClick = {
                                    viewModel.oneTapSignIn()
                                    Log.d("SignInScreen", "Sign In with Google")
                                }
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.ic_google_logo
                                    ),
                                    contentDescription = stringResource(
                                        id = R.string.signin_google
                                    ),
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(id = R.string.google),
                                    maxLines = 1
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.signup_offer),
                                fontSize = 14.sp,
                            )
                            TextButton(
                                onClick = navigateToSignUpScreen,
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                Text(
                                    text = stringResource(id = R.string.signup),
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
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
            Log.d("SignInScreen", "SignInWithGoogle")
            if (signedIn) {
                navigateToHomeScreen()
            }
        }
    )

    SignIn(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}

@PreviewLightDark
@Composable
fun SignInPreview() {
    PondPediaCustomTheme {
        SignInScreen(
            state = AuthState(),
            navigateToAuthScreen = {},
            navigateToSignUpScreen = {},
            navigateToHomeScreen = {},
        )
    }
}

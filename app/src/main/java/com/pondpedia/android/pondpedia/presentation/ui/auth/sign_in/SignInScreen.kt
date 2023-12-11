@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme
import com.pondpedia.android.pondpedia.presentation.theme.White
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent

@Composable
fun SignInScreen(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onEmailPasswordSignInClick: (String, String) -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {

    PondPediaCustomTheme (darkTheme = false) {
        SignInScreenLightMode(
            state = state,
            navigateToAuthScreen = navigateToAuthScreen,
            navigateToSignUpScreen = navigateToSignUpScreen,
            onGoogleSignInClick = onGoogleSignInClick,
            onEmailPasswordSignInClick = onEmailPasswordSignInClick,
            onEvent = onEvent
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreenLightMode(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onEmailPasswordSignInClick: (String, String) -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {
    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf(state.email) }

    var password by rememberSaveable { mutableStateOf(state.password) }

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }


//    Image(
//        painter = painterResource(R.drawable.underwater_light_blue),
//        contentDescription = "Background Image",
//        contentScale = ContentScale.FillBounds,
//        modifier = Modifier.fillMaxHeight()
//    )


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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.email_label),
                            maxLines = 1,
                        )
                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                                onEvent(AuthEvent.SetEmail(it))
                            },
                            placeholder = { Text(stringResource(R.string.email_label)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(.6f),
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = White,
                                unfocusedContainerColor = White,
                                disabledContainerColor = White,
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null
                                )
                            },
                            isError = state.emailError != null,
                            supportingText = {
                                if (state.emailError != null) {
                                    Text(text = state.emailError)
                                }
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.password_label),
                            maxLines = 1,
                        )
                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                                onEvent(AuthEvent.SetPassword(it))
                            },
                            placeholder = { Text(stringResource(R.string.password_label)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(.6f),
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = White,
                                unfocusedContainerColor = White,
                                disabledContainerColor = White,
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null
                                )
                            },
                            isError = state.passwordError != null,
                            supportingText = {
                                if (state.passwordError != null) {
                                    Text(text = state.passwordError)
                                }
                            }
                        )
                    }
                }

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
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.signin),
                                maxLines = 1
                            )
                        }

                        Spacer(modifier = Modifier.height(3.dp))
                        Divider(
                            modifier = Modifier.height(3.dp),
                            thickness = 1.dp
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onGoogleSignInClick
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
                                text = stringResource(id = R.string.signin_google),
                                maxLines = 1
                            )
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onGoogleSignInClick
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
                                text = stringResource(id = R.string.signin_facebook),
                                maxLines = 1
                            )
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
}

@PreviewLightDark
@Composable
fun SignInPreview() {
    PondPediaCustomTheme {
        SignInScreen(
            state = AuthState(),
            navigateToAuthScreen = {},
            navigateToSignUpScreen = {},
            onGoogleSignInClick = {},
            onEmailPasswordSignInClick = { _, _ -> },
            onEvent = { _ -> }
        )
    }
}

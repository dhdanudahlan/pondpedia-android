@file:OptIn(ExperimentalMaterial3Api::class)

package com.pondpedia.android.pondpedia.presentation.screens.auth.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.screens.authentication.components.AuthState
import com.pondpedia.android.pondpedia.presentation.ui.theme.PondPediaCustomTheme
import com.pondpedia.android.pondpedia.presentation.ui.theme.White

@Composable
fun SignInScreen(
    state: AuthState,
    onGoogleSignInClick: () -> Unit,
    onEmailPasswordSignInClick: (String, String) -> Unit,
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf(state.email) }
    val password = remember { mutableStateOf(state.password) }
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
                    IconButton(onClick = { /*TODO*/ }) {
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
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(80.dp))

                        Image(
                            painter = painterResource(R.drawable.pondpedia_1),
                            contentDescription = "Brush",
                            modifier = Modifier
                                .size(200.dp)
                                .alpha(0.75f),
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Column {
                            TextField(
                                value = email.value,
                                onValueChange = { email.value = it },
                                label = { Text("Email") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(.6f),
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = White,
                                    unfocusedContainerColor = White,
                                    disabledContainerColor = White,
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = password.value,
                                onValueChange = { password.value = it },
                                label = { Text("Password") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(.6f),
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = White,
                                    unfocusedContainerColor = White,
                                    disabledContainerColor = White,
                                ),
                                visualTransformation = PasswordVisualTransformation()
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEmailPasswordSignInClick(
                                email.value,
                                password.value
                            )
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.signin),
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onGoogleSignInClick
                    ) {
                        Text(
                            text = stringResource(id = R.string.signin_google),
                            maxLines = 1
                        )
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
            onGoogleSignInClick = {},
            onEmailPasswordSignInClick = { _, _ -> },
        )
    }
}

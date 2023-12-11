package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable.InformationSourceTabScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable.OccupationTabScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.*
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme
import com.pondpedia.android.pondpedia.presentation.theme.White

@Composable
fun SignUpScreen(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    onEmailPasswordSignUpClick: (String, String) -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {
    PondPediaCustomTheme (darkTheme = false) {
        SignUpScreenLightMode(
            state = state,
            navigateToAuthScreen = navigateToAuthScreen,
            navigateToSignInScreen = navigateToSignInScreen,
            onEmailPasswordSignUpClick = onEmailPasswordSignUpClick,
            onEvent = onEvent
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenLightMode(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    onEmailPasswordSignUpClick: (String, String) -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {
    val context = LocalContext.current

    var name by rememberSaveable { mutableStateOf(state.name) }

    var email by rememberSaveable { mutableStateOf(state.email) }

    var phoneNumber by rememberSaveable { mutableStateOf(state.phoneNumber) }

    var password by rememberSaveable { mutableStateOf(state.password) }

    var repeatedPassword by rememberSaveable { mutableStateOf(state.repeatedPassword) }

    var occupation by rememberSaveable { mutableStateOf(state.occupation) }

    var informationSource by rememberSaveable { mutableStateOf(state.informationSource) }

    var acceptedTerms by rememberSaveable { mutableStateOf(state.acceptedTerms) }


    val sheetState = rememberModalBottomSheetState()


    var isOccupationSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isInformationSourceOpen by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_signup_page))
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
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
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

                        Spacer(modifier = Modifier.height(32.dp))

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
                                    text = stringResource(R.string.name_label),
                                    maxLines = 1,

                                )
                                TextField(
                                    value = name,
                                    onValueChange = { 
                                        name = it
                                        onEvent(SetName(it))
                                    },
                                    placeholder = { Text(stringResource(R.string.name_label)) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .alpha(.6f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = White,
                                        unfocusedContainerColor = White,
                                        disabledContainerColor = White,
                                    ),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null
                                        )
                                    },
                                    isError = state.nameError != null,
                                    supportingText = {
                                        if (state.nameError != null) {
                                            Text(text = state.nameError)
                                        }
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

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
                                        onEvent(SetEmail(it))
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
                        Spacer(modifier = Modifier.height(16.dp))

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
                                    text = stringResource(R.string.number_label),
                                    maxLines = 1,
                                )
                                TextField(
                                    value = phoneNumber,
                                    onValueChange = {
                                        phoneNumber = it
                                        onEvent(SetPhoneNumber(it))
                                    },
                                    placeholder = { Text(stringResource(R.string.number_label)) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .alpha(.6f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = White,
                                        unfocusedContainerColor = White,
                                        disabledContainerColor = White,
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.PhoneAndroid,
                                            contentDescription = null
                                        )
                                    },
                                    isError = state.phoneNumberError != null,
                                    supportingText = {
                                        if (state.phoneNumberError != null) {
                                            Text(text = state.phoneNumberError)
                                        }
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

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
                                        onEvent(SetPassword(it))
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
                        Spacer(modifier = Modifier.height(16.dp))

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
                                    text = stringResource(id = R.string.password_confirmation_label),
                                    maxLines = 1,
                                )
                                TextField(
                                    value = repeatedPassword,
                                    onValueChange = {
                                        repeatedPassword = it
                                        onEvent(SetRepeatedPassword(it))
                                    },
                                    placeholder = { Text(stringResource(R.string.password_confirmation_label)) },
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
                                    isError = state.repeatedPasswordError != null,
                                    supportingText = {
                                        if (state.repeatedPasswordError != null) {
                                            Text(text = state.repeatedPasswordError)
                                        }
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

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
                                    text = stringResource(id = R.string.occupation_label),
                                    maxLines = 1,
                                )
                                ExposedDropdownMenuBox(
                                    expanded = isOccupationSheetOpen,
                                    onExpandedChange = { newValue ->
                                        isOccupationSheetOpen = newValue
                                    }
                                ) {
                                    TextField(
                                        value = occupation,
                                        onValueChange = {
                                            occupation = it
                                            onEvent(SetOccupation(it))
                                        },
                                        placeholder = { Text(stringResource(R.string.choose_label)) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .alpha(.6f)
                                            .menuAnchor(),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = White,
                                            unfocusedContainerColor = White,
                                            disabledContainerColor = White,
                                        ),
                                        readOnly = true,
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Work,
                                                contentDescription = null
                                            )
                                        },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isOccupationSheetOpen)
                                        },
                                        isError = state.occupationError != null,
                                        supportingText = {
                                            if (state.occupationError != null) {
                                                Text(text = state.occupationError)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

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
                                    text = stringResource(id = R.string.information_source_label),
                                    maxLines = 2,
                                )
                                ExposedDropdownMenuBox(
                                    expanded = isInformationSourceOpen,
                                    onExpandedChange = { newValue ->
                                        isInformationSourceOpen = newValue
                                    }
                                ) {
                                    TextField(
                                        value = informationSource,
                                        onValueChange = {
                                            informationSource = it
                                            onEvent(SetInformationSource(it))
                                        },
                                        placeholder = { Text(stringResource(R.string.choose_label)) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .alpha(.6f)
                                            .menuAnchor(),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = White,
                                            unfocusedContainerColor = White,
                                            disabledContainerColor = White,
                                        ),
                                        readOnly = true,
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Lightbulb,
                                                contentDescription = null
                                            )
                                        },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isInformationSourceOpen)
                                        },
                                        isError = state.informationSourceError != null,
                                        supportingText = {
                                            if (state.informationSourceError != null) {
                                                Text(text = state.informationSourceError)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            contentAlignment = Alignment.CenterStart
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxSize(),
//                                horizontalAlignment = Alignment.Start
//                            ) {
//                                Row(
//                                    verticalAlignment = Alignment.CenterVertically
//                                ) {
//
//                                    Checkbox(
//                                        checked = state.acceptedTerms,
//                                        onCheckedChange = {
//                                            acceptedTerms = it
//                                            onEvent(SetAcceptTerms(it))
//                                        }
//                                    )
//                                    Text(
//                                        text = stringResource(id = R.string.accept_terms_label),
//                                        maxLines = 2,
//                                    )
//                                }
//
//                                if (state.acceptedTermsError != null) {
//                                    Text(
//                                        text = state.acceptedTermsError,
//                                        maxLines = 2,
//                                        color = MaterialTheme.colorScheme.error
//                                    )
//                                }
//                            }
//                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                /*onEmailPasswordSignUpClick(
                                    email,
                                    password
                                )*/

                                Log.d("SignUpScreen", "Button is Clicked")
                                onEvent(SignUp)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.signup))
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.signin_offer),
                                fontSize = 14.sp,
                            )
                            TextButton(
                                onClick = navigateToSignInScreen,
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                Text(
                                    text = stringResource(id = R.string.signin),
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }
            }
        }

        if (isOccupationSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isOccupationSheetOpen = false
                },
                modifier = Modifier.fillMaxSize()
            ) {
                OccupationTabScreen(
                    onClick = { selectedItem ->
                        occupation = selectedItem
                        onEvent(SetOccupation(selectedItem))
                        isOccupationSheetOpen = false
                    }
                )
            }
        }
        if (isInformationSourceOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isInformationSourceOpen = false
                },
                modifier = Modifier.fillMaxSize()
            ) {
                InformationSourceTabScreen(
                    onClick = { selectedItem ->
                        informationSource = selectedItem
                        onEvent(SetInformationSource(selectedItem))
                        isInformationSourceOpen = false
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun SignUpPreview() {
    PondPediaCustomTheme {
        SignUpScreen(
            state = AuthState(),
            navigateToAuthScreen = {},
            navigateToSignInScreen = {},
            onEmailPasswordSignUpClick = { _, _ -> },
            onEvent = { event ->

            }
        )
    }
}
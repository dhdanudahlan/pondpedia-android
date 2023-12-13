package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.components.BackIcon
import com.pondpedia.android.pondpedia.components.LargeSpacer
import com.pondpedia.android.pondpedia.core.util.Utils.Companion.showMessage
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.MyEmailTextField
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.MyOutlineDropdownTextField
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.MyOutlineTextField
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable.MyPasswordTextField
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetEmail
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetInformationSource
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetName
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetOccupation
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetPassword
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetPhoneNumber
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent.SetRepeatedPassword
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable.InformationSourceTabScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable.OccupationTabScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable.SendEmailVerification
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable.SignUp
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {
    PondPediaCustomTheme (darkTheme = false) {
        SignUpScreenLightMode(
            state = state,
            navigateToAuthScreen = navigateToAuthScreen,
            navigateToSignInScreen = navigateToSignInScreen,
            onEvent = onEvent
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenLightMode(
    viewModel: SignUpViewModel = hiltViewModel(),
    state: AuthState,
    navigateToAuthScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {
    val context = LocalContext.current

//    LaunchedEffect(key1 = state.signInError) {
//        state.signInError?.let { error ->
//            Toast.makeText(
//                context,
//                error,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var name by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.name
                )
            )
        }
    )

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

    var phoneNumber by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.phoneNumber
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

    var repeatedPassword by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.repeatedPassword
                )
            )
        }
    )

    var occupation by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.occupation
                )
            )
        }
    )

    var informationSource by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.informationSource
                )
            )
        }
    )

    var acceptedTerms by rememberSaveable { mutableStateOf(state.acceptedTerms) }

    val keyboard = LocalSoftwareKeyboardController.current

    val sheetState = rememberModalBottomSheetState()


    var isOccupationSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isInformationSourceOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_signup_page)
                    )
                },
                navigationIcon = {
                    BackIcon(
                        navigateBack = navigateToAuthScreen
                    )
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

                        MyOutlineTextField(
                            textFieldValue = name,
                            onTextFieldValueChange = { newValue ->
                                name = newValue
                                onEvent(SetName(newValue.text))
                            },
                            isErrorMessage = state.nameError,
                            label = stringResource(R.string.name_label),
                            imageVector = Icons.Outlined.Person
                        )

                        LargeSpacer()

                        MyEmailTextField(
                            email = email,
                            onEmailValueChange = { newValue ->
                                email = newValue
                                onEvent(SetEmail(newValue.text))
                            },
                            isErrorMessage = state.emailError
                        )

                        LargeSpacer()

                        MyOutlineTextField(
                            textFieldValue = phoneNumber,
                            onTextFieldValueChange = { newValue ->
                                phoneNumber = newValue
                                onEvent(SetPhoneNumber(newValue.text))
                            },
                            isErrorMessage = state.phoneNumberError,
                            label = stringResource(R.string.number_label),
                            imageVector = Icons.Outlined.PhoneAndroid
                        )

                        LargeSpacer()

                        MyPasswordTextField(
                            password = password,
                            onPasswordValueChange = { newValue ->
                                password = newValue
                                onEvent(SetPassword(newValue.text))
                            },
                            isErrorMessage = state.passwordError
                        )

                        LargeSpacer()

                        MyPasswordTextField(
                            password = repeatedPassword,
                            onPasswordValueChange = { newValue ->
                                repeatedPassword = newValue
                                onEvent(SetRepeatedPassword(newValue.text))
                            },
                            isErrorMessage = state.repeatedPasswordError
                        )

                        LargeSpacer()

                        MyOutlineDropdownTextField(
                            textFieldValue = occupation,
                            onTextFieldValueChange = { newValue ->
                                occupation = newValue
                                onEvent(SetOccupation(newValue.text))
                            },
                            onExpandedChange = { newValue ->
                                isOccupationSheetOpen = newValue
                            },
                            isExpanded = isOccupationSheetOpen,
                            isErrorMessage = state.occupationError,
                            label = stringResource(id = R.string.occupation_label),
                            imageVector = Icons.Outlined.Work
                        )

                        LargeSpacer()

                        MyOutlineDropdownTextField(
                            textFieldValue = informationSource,
                            onTextFieldValueChange = { newValue ->
                                informationSource = newValue
                                onEvent(SetInformationSource(newValue.text))
                            },
                            onExpandedChange = { newValue ->
                                isInformationSourceOpen = newValue
                            },
                            isExpanded = isInformationSourceOpen,
                            isErrorMessage = state.informationSourceError,
                            label = stringResource(id = R.string.information_source_label),
                            imageVector = Icons.Outlined.Lightbulb
                        )

                        LargeSpacer()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
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
                        }
                    }
                }
                LargeSpacer()

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
                                keyboard?.hide()
                                onEvent(AuthEvent.SignUp)
                                viewModel.signUpWithEmailAndPassword(email.text, password.text, name.text)
                                onEvent(AuthEvent.Reset)
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
                        occupation = TextFieldValue(selectedItem)
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
                        informationSource = TextFieldValue(selectedItem)
                        onEvent(SetInformationSource(selectedItem))
                        isInformationSourceOpen = false
                    }
                )
            }
        }
    }

    val verifyEmailMessage = stringResource(R.string.verify_email_message)
    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, verifyEmailMessage)
        }
    )

    SendEmailVerification()
}

@PreviewLightDark
@Composable
fun SignUpPreview() {
    PondPediaCustomTheme {
        SignUpScreen(
            state = AuthState(),
            navigateToAuthScreen = {},
            navigateToSignInScreen = {},
            onEvent = { _ ->

            }
        )
    }
}
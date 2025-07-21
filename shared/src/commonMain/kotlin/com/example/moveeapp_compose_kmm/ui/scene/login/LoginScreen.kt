package com.example.moveeapp_compose_kmm.ui.scene.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.data.account.LoginState
import com.example.moveeapp_compose_kmm.ui.components.TextInputItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme
import com.example.moveeapp_compose_kmm.utils.Constants
import movee.shared.generated.resources.Res
import movee.shared.generated.resources.ic_login_movee
import movee.shared.generated.resources.login_background
import movee.shared.generated.resources.login_forgot_password
import movee.shared.generated.resources.login_password
import movee.shared.generated.resources.login_register
import movee.shared.generated.resources.login_title
import movee.shared.generated.resources.login_username
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToWebViewScreen: (String) -> Unit,
    navigateToMainScreen: () -> Unit,
) {
    val loginState by viewModel.isLoggedIn.collectAsState()

    LoginScreen(
        viewModel.loginUiState,
        loginState,
        viewModel::onUserNameChange,
        viewModel::onPasswordChange,
        viewModel::login,
        navigateToWebViewScreen,
        navigateToMainScreen
    )
}

@Composable
private fun LoginScreen(
    loginUiState: LoginUiState,
    loginState: LoginState,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    navigateToWebViewScreen: (String) -> Unit,
    navigateToMainScreen: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxHeight(0.35f),
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(Res.drawable.ic_login_movee),
                    contentDescription = null
                )
            }

            LoginContent(
                loginUiState = loginUiState,
                onUserNameChange = onUserNameChange,
                onPasswordChange = onPasswordChange,
                onLogin = onLogin,
                isLoggedIn = loginState,
                navigateToWebViewScreen = navigateToWebViewScreen,
                navigateToMainScreen = navigateToMainScreen,
            )
        }
    }
}

@Composable
private fun LoginContent(
    loginUiState: LoginUiState,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    isLoggedIn: LoginState,
    navigateToWebViewScreen: (String) -> Unit,
    navigateToMainScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (loginUiState.loginError != null) {
            Text(text = loginUiState.loginError, color = Color.White)
        }

        TextInputItem(
            modifier = Modifier.fillMaxWidth(),
            query = loginUiState.userName,
            onValueChange = { onUserNameChange(it) },
            label = {
                TextItem(
                    text = stringResource(Res.string.login_username),
                    textColor = MaterialTheme.colorScheme.primaryContainer
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    tint = Color.White,
                    contentDescription = stringResource(Res.string.login_username)
                )
            },
            isError = loginUiState.loginError != null
        )

        TextInputItem(
            modifier = Modifier.fillMaxWidth(),
            query = loginUiState.password,
            onValueChange = { onPasswordChange(it) },
            label = {
                TextItem(
                    text = stringResource(Res.string.login_password),
                    textColor = MaterialTheme.colorScheme.primaryContainer
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    tint = Color.White,
                    contentDescription = stringResource(Res.string.login_password)
                )
            },
            isError = loginUiState.loginError != null,
            visualTransformation = PasswordVisualTransformation()
        )

        Row {
            TextItem(
                text = stringResource(Res.string.login_forgot_password),
                modifier = Modifier.clickable {
                    navigateToWebViewScreen(Constants.FORGOT_PASSWORD)
                },
                textColor = MaterialTheme.colorScheme.primaryContainer
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Blue
            ),
            onClick = { onLogin() }) {
            TextItem(
                text = stringResource(Res.string.login_title),
                textColor = MaterialTheme.colorScheme.primary
            )
        }

        TextItem(
            text = stringResource(Res.string.login_register),
            modifier = Modifier.clickable {
                navigateToWebViewScreen(Constants.REGISTER)
            },
            textColor = MaterialTheme.colorScheme.primaryContainer
        )

        LaunchedEffect(key1 = isLoggedIn) {
            if (isLoggedIn == LoginState.LOGGED_IN) {
                navigateToMainScreen()
            }
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    AppTheme {
        LoginScreen(
            loginUiState = LoginUiState(),
            loginState = LoginState.LOGGED_OUT,
            onUserNameChange = {},
            onPasswordChange = {},
            onLogin = {},
            navigateToWebViewScreen = {},
            navigateToMainScreen = {}
        )
    }
}

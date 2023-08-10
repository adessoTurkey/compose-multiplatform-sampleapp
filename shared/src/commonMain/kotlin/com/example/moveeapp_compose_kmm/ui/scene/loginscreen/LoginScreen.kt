package com.example.moveeapp_compose_kmm.ui.scene.loginscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.ui.components.TextInputItem
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
    onNavToHomePage: () -> Unit,
    viewModel: LoginViewModel = koinInject()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginContent(
            loginUiState = viewModel.loginUiState,
            onUserNameChange = viewModel::onUserNameChange,
            onPasswordChange = viewModel::onPasswordChange,
            onLogin = viewModel::login,
            onNavToHomePage = onNavToHomePage,
            hasUser = viewModel.hasUser
        )
    }
}

@Composable
fun LoginContent(
    loginUiState: LoginUiState,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onNavToHomePage: () -> Unit,
    hasUser: LoginState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (loginUiState.loginError != null) {
            Text(text = loginUiState.loginError)
        }

        TextInputItem(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            query = loginUiState.userName,
            onValueChange = { onUserNameChange(it) },
            label = { Text("Username") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
            },
            isError = loginUiState.loginError != null
        )

        TextInputItem(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            query = loginUiState.password,
            onValueChange = { onPasswordChange(it) },
            label = { Text("Password") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            },
            isError = loginUiState.loginError != null,
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = { onLogin() }) {
            Text(text = "Sign In")
        }

        LaunchedEffect(key1 = hasUser) {
            if (hasUser == LoginState.LOGGED_IN) {
                onNavToHomePage.invoke()
            }
        }
    }
}





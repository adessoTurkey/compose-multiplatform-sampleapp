package com.example.moveeapp_compose_kmm.ui.scene.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.ui.components.TextInputItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    onNavToHomePage: () -> Unit,
    viewModel: LoginViewModel = koinInject()
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = org.jetbrains.compose.resources.painterResource("MR/image/login_background.png"),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxHeight(0.5f),
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = org.jetbrains.compose.resources.painterResource("MR/image/movee_icon.png"),
                    contentDescription = null
                )
            }

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
            Text(text = loginUiState.loginError, color = Color.White)
        }

        TextInputItem(
            modifier = Modifier.fillMaxWidth(),
            query = loginUiState.userName,
            onValueChange = { onUserNameChange(it) },
            label = { Text(text = "Username") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    tint = Color.White,
                    contentDescription = "Username"
                )
            },
            isError = loginUiState.loginError != null
        )

        TextInputItem(
            modifier = Modifier.fillMaxWidth(),
            query = loginUiState.password,
            onValueChange = { onPasswordChange(it) },
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    tint = Color.White,
                    contentDescription = "Password"
                )
            },
            isError = loginUiState.loginError != null,
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Blue),
            onClick = { onLogin() }) {
            Text(text = "Login")
        }

        LaunchedEffect(key1 = hasUser) {
            if (hasUser == LoginState.LOGGED_IN) {
                onNavToHomePage.invoke()
            }
        }
    }
}





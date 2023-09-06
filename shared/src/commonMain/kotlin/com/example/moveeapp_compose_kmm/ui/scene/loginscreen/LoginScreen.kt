package com.example.moveeapp_compose_kmm.ui.scene.loginscreen

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
import androidx.compose.material3.*
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.MainScreen
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.data.repository.LoginState
import com.example.moveeapp_compose_kmm.ui.components.TextInputItem
import com.example.moveeapp_compose_kmm.ui.scene.webviewscreen.WebViewScreen
import com.example.moveeapp_compose_kmm.utils.Constants
import dev.icerock.moko.resources.compose.painterResource

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: LoginViewModel = viewModel()
        LoginScreen(viewModel = viewModel, navigator = navigator)
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel, navigator: Navigator
) {
    val isLoggedState by viewModel.isLoggedIn.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(MR.images.login_background),
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
                    painter = painterResource(MR.images.ic_login_movee),
                    contentDescription = null
                )
            }

            LoginContent(
                loginUiState = viewModel.loginUiState,
                onUserNameChange = viewModel::onUserNameChange,
                onPasswordChange = viewModel::onPasswordChange,
                onLogin = viewModel::login,
                navigator = navigator,
                isLoggedIn = isLoggedState
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
    navigator: Navigator,
    isLoggedIn: LoginState
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

        Row {
            Text(
                text = "Forgot Password?",
                modifier = Modifier.clickable {
                    navigateToWebViewScreen(url = Constants.FORGOT_PASSWORD, navigator = navigator)
                },
                color = MaterialTheme.colorScheme.primaryContainer
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
            Text(text = "Login")
        }

        Text(
            text = "Don't have an account? Register Now",
            modifier = Modifier.clickable {
                navigateToWebViewScreen(url = Constants.REGISTER, navigator = navigator)
            },
            color = MaterialTheme.colorScheme.primaryContainer
        )

        LaunchedEffect(key1 = isLoggedIn) {
            if (isLoggedIn == LoginState.LOGGED_IN) {
                navigator.replace(MainScreen)
            }
        }
    }
}

fun navigateToWebViewScreen(url: String, navigator: Navigator) {
    navigator.push(WebViewScreen(url))
}

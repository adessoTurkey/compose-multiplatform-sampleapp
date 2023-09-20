package com.example.moveeapp_compose_kmm.ui.scene.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.components.TextItem

class AccountScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AccountDetailViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        SuccessContent(uiState)
    }
}

@Composable
fun SuccessContent(uiState: AccountUiState) {
    TextItem(text = uiState.accountData.accountId.toString())
    TextItem(text = uiState.accountData.username)
}
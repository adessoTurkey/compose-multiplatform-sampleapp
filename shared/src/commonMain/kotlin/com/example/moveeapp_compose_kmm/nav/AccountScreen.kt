package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.account.AccountDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.account.AccountScreen

class AccountScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val mainNavigator = LocalMainNavigator.current
        val viewModel: AccountDetailViewModel = viewModel()

        AccountScreen(
            viewModel = viewModel,
            navigateToSplash = { mainNavigator.replaceAll(SplashScreen()) },
            navigateToFavorite = { navigator.push(FavoriteScreen(it)) })

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}

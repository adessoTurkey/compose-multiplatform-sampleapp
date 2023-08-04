package com.example.moveeapp_compose_kmm.navigation

import androidx.compose.runtime.Composable
import com.example.moveeapp_compose_kmm.ui.scene.detailscreen.DetailScreen
import com.example.moveeapp_compose_kmm.ui.scene.homescreen.HomeScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Navigation(navigator: Navigator) {
    NavHost(
        navigator = navigator,
        initialRoute = Route.Home.route
    ){
        scene(route = Route.Home.route){
            HomeScreen(navigator, onClick = {
                navigator.navigate(Route.Detail.route)
            })
        }
        scene(route = Route.Detail.route){
            DetailScreen(navigator, onClick = {
                navigator.navigate(Route.Home.route)
            })
        }
    }
}
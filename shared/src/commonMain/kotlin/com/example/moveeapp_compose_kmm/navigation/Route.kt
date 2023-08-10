package com.example.moveeapp_compose_kmm.navigation

sealed class Route(val route: String){

    object Home : Route("home_screen")
    object Detail : Route("detail_screen")
    object Login : Route("login_screen")
}

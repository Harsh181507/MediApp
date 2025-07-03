package com.example.mediapp.screens.nav

import kotlinx.serialization.Serializable

sealed class Routs(val route: String) {
    object SignUpScreenRoutes : Routs("signup_screen")
    object WaitingScreenRoutes : Routs("waiting_screen")
    object MainScreenRoutes : Routs("main_screen")
    object LoginScreenRoutes : Routs("login_screen")
    object TabScreenRoutes : Routs("tab_screen")
    object AddOrderScreenRoutes : Routs("add_order_screen")
    object HomeScreenRoutes : Routs("home_screen")

}

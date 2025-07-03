package com.example.mediapp.screens.nav

import android.content.pm.LauncherApps
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediapp.screens.AddOrderScreen
import com.example.mediapp.screens.LoginScreen
import com.example.mediapp.screens.MainScreen
import com.example.mediapp.screens.SignUpScreen
import com.example.mediapp.screens.TabScreen
import com.example.mediapp.screens.WaitingScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediapp.viewModel.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@Composable
fun App(viewModel: MyViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val token = remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var selected by remember { mutableIntStateOf(0) }
    val userId  = viewModel._userIdByPref.collectAsState()
    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getUserByPref()
        }
    }

    val BottomNavItem = listOf(
        BottomNavItem("Home", Icons.Default.Home, Icons.Outlined.Home),
        BottomNavItem("Product", Icons.Default.ShoppingCart, Icons.Outlined.ShoppingCart),
        BottomNavItem("Order", Icons.Default.Person, Icons.Outlined.Person),
        BottomNavItem("Stock", Icons.Default.AccountBox, Icons.Outlined.AccountBox)

    )

    val startScreen = remember(userId.value) {
        if (userId.value!!.isEmpty()){
            Routs.SignUpScreenRoutes
        }else{
            Routs.WaitingScreenRoutes
        }
    }




    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavItem.forEachIndexed { index,bottomNavItem->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                            when (index) {
                                0 -> navController.navigate(Routs.HomeScreenRoutes.route)
                                1 -> navController.navigate(Routs.TabScreenRoutes.route)
                                2 -> navController.navigate(Routs.AddOrderScreenRoutes.route)
                                3 -> navController.navigate(Routs.MainScreenRoutes.route)
                            }
                        },
                        icon = {
                            if (selected == index) {
                                bottomNavItem.icon
                            } else {
                                bottomNavItem.unselectedIcon
                            }
                        },
                        label =  {
                            Text(text =bottomNavItem.name)

                        }
                    )
                }
            }
        }
    ) {
        innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            when(selected){
                0 -> navController.navigate(Routs.HomeScreenRoutes.route)
                1 -> navController.navigate(Routs.TabScreenRoutes.route)
                2 -> navController.navigate(Routs.AddOrderScreenRoutes.route)
                3 -> navController.navigate(Routs.MainScreenRoutes.route)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){}

        NavHost(
            navController = navController,
            startDestination = startScreen
        ) {
            composable(Routs.SignUpScreenRoutes.route) {
                SignUpScreen(navController = navController)
            }
            composable(Routs.WaitingScreenRoutes.route) {
                WaitingScreen(navController = navController)
            }
            composable(Routs.MainScreenRoutes.route) {
                MainScreen(navController = navController)
            }
            composable(Routs.LoginScreenRoutes.route) {
                LoginScreen(navController = navController)
            }
            composable(Routs.TabScreenRoutes.route) {
                TabScreen(navController = navController)
            }
            composable(Routs.AddOrderScreenRoutes.route) {
                AddOrderScreen(navController = navController)
            }
        }
    }

}
data class BottomNavItem(
    val name: String,
    val icon : ImageVector,
    val unselectedIcon: ImageVector
)

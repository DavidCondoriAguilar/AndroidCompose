package com.davidchura.sistema1228

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import com.davidchura.sistema1228.pages.*

val Green = Color(0xFF508D4E)

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Sistema1228Theme {
                val navController = rememberNavController()
                var selectedTab by remember { mutableStateOf("home") }

                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Green,
                            contentColor = Color.White
                        ) {
                            val tabs = listOf(
                                BottomNavItem("Home", Icons.Filled.Home, "home"),
                                BottomNavItem("Clases", Icons.Filled.Star, "clases"),
                                BottomNavItem("Workout", Icons.Filled.PlayArrow, "workout"),
                                BottomNavItem("Perfil", Icons.Filled.Person, "perfil")
                            )

                            tabs.forEach { tab ->
                                BottomNavigationItem(
                                    icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                                    label = { Text(tab.label) },
                                    selected = selectedTab == tab.route,
                                    onClick = {
                                        selectedTab = tab.route
                                        navController.navigate(tab.route) {
                                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    selectedContentColor = Color.White,
                                    unselectedContentColor = Color.Gray
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("home") { DirectoresScreen() }
                        composable("clases") { ProveedoresScreen() }
                        composable("workout") { DirectoresScreen() }
                        composable("perfil") { DirectoresScreen() }
                    }
                }
            }
        }
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

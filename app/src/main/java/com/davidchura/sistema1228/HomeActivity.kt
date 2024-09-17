package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import com.davidchura.sistema1228.R
import com.davidchura.sistema1228.pages.*

// Colores personalizados
val Green = Color(0xFF508D4E)
val GreenDark = Color(0xFF1A5319)

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Sistema1228Theme {
                var selectedTab by remember { mutableStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Green,
                            contentColor = Color.White
                        ) {
                            // Define tabs within this scope
                            val tabs = listOf(
                                BottomNavItem("Home", Icons.Filled.Home, 0),
                                BottomNavItem("Clases", Icons.Filled.Star, 1),
                                BottomNavItem("Workout", Icons.Filled.LocationOn, 2),
                                BottomNavItem("Perfil", Icons.Filled.Person, 3)
                            )

                            tabs.forEach { tab ->
                                BottomNavigationItem(
                                    icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                                    label = { Text(tab.label) },
                                    selected = selectedTab == tab.index,
                                    onClick = {
                                        selectedTab = tab.index
                                        val intent = when (tab.index) {
                                            0 -> Intent(this@HomeActivity, HomeActivity::class.java)
                                            1 -> Intent(this@HomeActivity, Proveedores::class.java)
                                            2 -> Intent(this@HomeActivity, Proveedores::class.java)
                                            3 -> Intent(this@HomeActivity, Proveedores::class.java)
                                            else -> Intent(this@HomeActivity, HomeActivity::class.java)
                                        }
                                        startActivity(intent)
                                    },
                                    selectedContentColor = Color.White,
                                    unselectedContentColor = Color.Gray
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = innerPadding,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(4) { index -> // Use a fixed number for grid items for now
                            GridItem(text = "Item $index", icon = Icons.Filled.PlayArrow) {
                                // Placeholder action for GridItem click
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GridItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, GreenDark),
        elevation = CardDefaults.elevatedCardElevation(4.dp) // Use CardDefaults.elevatedCardElevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Green
            )
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
        }
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector, val index: Int)

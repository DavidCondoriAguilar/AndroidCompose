package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import com.davidchura.sistema1228.R // Asegúrate de importar correctamente R
import com.davidchura.sistema1228.pages.DirectoresActivity
import com.davidchura.sistema1228.pages.Proveedores
import com.davidchura.sistema1228.pages.TiendaActivity

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val etiquetas = arrayOf("Proveedores", "Empleados", "Tienda", "Directores", "Clientes", "Salir")
        val iconos = arrayOf(
            Icons.Default.Build,
            Icons.Default.AccountCircle,
            Icons.Default.Face,
            Icons.Default.Edit,
            Icons.Filled.ShoppingCart,
            Icons.Sharp.Star
        )

        val destinations = arrayOf(
            Proveedores::class.java,
            TiendaActivity::class.java,
            DirectoresActivity::class.java,
        )

        setContent {
            Sistema1228Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.secondary,
                            ),
                            title = {
                                Text(stringResource(id = R.string.home))
                            }
                        )
                    },
                ) { innerPadding ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Dos columnas
                        contentPadding = innerPadding, // Aplica el padding de Scaffold
                        horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado horizontal
                        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado vertical
                    ) {
                        items(etiquetas.size) { index ->
                            GridItem(text = etiquetas[index], icon = iconos[index]) {
                                val intent = Intent(this@HomeActivity, destinations[index])
                                startActivity(intent)
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
    val fontFamily = FontFamily(
        Font(R.font.urbanist_bold) // Reemplaza con el recurso de fuente que desees usar
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp) // Altura fija para los ítems
            .padding(8.dp) // Espaciado entre los ítems
            .clickable(onClick = onClick), // Añade la acción de clic
        shape = RoundedCornerShape(12.dp), // Bordes redondeados
        border = BorderStroke(1.dp, Color.Gray), // Borde de 1 dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Padding interno
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 12.dp), // Espacio entre ícono y texto
                tint = MaterialTheme.colorScheme.primary // Color del ícono
            )
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 18.sp, // Tamaño del texto
                    color = Color.Black // Color del texto
                )
            )
        }
    }
}

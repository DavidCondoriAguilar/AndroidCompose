package com.davidchura.sistema1228.pages

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.content.Proveedor
import com.davidchura.sistema1228.network.fetchProveedores
import com.davidchura.sistema1228.ui.theme.Green
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Proveedores : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                ProveedoresScreen(context = this)
            }
        }
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProveedoresScreen(context: Context) {
    val scope = rememberCoroutineScope() // Crea un CoroutineScope
    var proveedores by remember { mutableStateOf(listOf<Proveedor>()) }
    var isLoading by remember { mutableStateOf(true) }

    if (isLoading) {
        scope.launch {
            fetchProveedores(context) { result ->
                proveedores = result
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trainers") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Green,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                // Mostrar texto simple mientras se cargan los datos
                Text(text = "Cargando proveedores...", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(proveedores) { proveedor ->
                        ProveedorCard(proveedor)
                    }
                }
            }
        }
    }
}


@Composable
fun ProveedorCard(proveedor: Proveedor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = proveedor.nombreEmpresa, style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Contacto: ${proveedor.nombreContacto} (${proveedor.cargoContacto})")
            Text(text = "Ciudad: ${proveedor.ciudad}")
            Text(text = "País: ${proveedor.pais}")
            Text(text = "Teléfono: ${proveedor.telefono}")
            proveedor.fax?.let {
                Text(text = "Fax: $it")
            }
        }
    }
}

package com.davidchura.sistema1228.pages

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.davidchura.sistema1228.content.Empleado
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import fetchEmpleados

class EmpleadoActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                EmpleadoScreen(this)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpleadoScreen(context: Context) {
    var empleados by remember { mutableStateOf(emptyList<Empleado>()) }
    var isLoading by remember { mutableStateOf(true) }

    // Cargar los datos de los empleados
    LaunchedEffect(Unit) {
        fetchEmpleados(context) { result ->
            empleados = result
            isLoading = false
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Empleados") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(empleados) { empleado ->
                        EmpleadoCard(empleado)
                    }
                }
            }
        }
    }
}

@Composable
fun EmpleadoCard(empleado: Empleado) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageUrl = "https://servicios.campus.pe/fotos/${empleado.foto}"
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(bottom = 8.dp)
            )
            Text("Nombre: ${empleado.tratamiento} ${empleado.nombres} ${empleado.apellidos}")
            Text("Cargo: ${empleado.cargo}")
            Text("Teléfono: ${empleado.telefono}")
            Text("Dirección: ${empleado.direccion}, ${empleado.ciudad}, ${empleado.pais}")
        }
    }
}


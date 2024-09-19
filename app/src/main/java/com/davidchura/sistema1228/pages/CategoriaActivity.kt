package com.davidchura.sistema1228.pages

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1228.Green
import com.davidchura.sistema1228.content.Categorias
import com.davidchura.sistema1228.content.fetchCategorias
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CategoriaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1228Theme {
                CategoriasScreen(context = this) // Pasar el contexto
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CategoriasScreen(context: Context) {
    val scope = rememberCoroutineScope()
    var categorias by remember { mutableStateOf(listOf<Categorias>()) }
    var isLoading by remember { mutableStateOf(true) }

    // Cargar datos al iniciar la composición
    if (isLoading) {
        scope.launch {
            fetchCategorias(context) { result ->
                categorias = result
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categorías") },
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
                Text(text = "Cargando categorías...", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(categorias) { categoria ->
                        CategoriaCard(categoria)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoriaCard(categoria: Categorias) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = categoria.nombre, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = categoria.descripcion, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Total: ${categoria.total}", style = MaterialTheme.typography.bodyMedium)
            // Aquí podrías agregar la imagen, usando una librería como Coil o similar
        }
    }
}

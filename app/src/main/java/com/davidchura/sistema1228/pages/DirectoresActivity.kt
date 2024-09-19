package com.davidchura.sistema1228.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1228.R
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

data class Director(val name: String, val position: String, val imageRes: Int)

class DirectoresActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Directores") }) },
                    content = { innerPadding ->
                        DirectoresScreen(modifier = Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}

@Composable
fun DirectoresScreen(modifier: Modifier = Modifier) {
    val directors = listOf(
        Director("Juan Pérez", "Director General", R.drawable.ic_director1),
        Director("Ana Gómez", "Directora de Ventas", R.drawable.ic_director2),
        Director("Luis Martínez", "Director de Operaciones", R.drawable.ic_director3)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bienvenido a la sección de Directores",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Aquí puedes gestionar a los trainers y su información.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(directors) { director ->
                DirectorCard(director)
            }
        }
    }
}

@Composable
fun DirectorCard(director: Director) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = director.imageRes),
                contentDescription = stringResource(id = R.string.begin_image_description),
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(
                    text = director.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = director.position,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

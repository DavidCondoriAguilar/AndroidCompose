package com.davidchura.sistema1228.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

data class Producto(
    val idproducto: String,
    val nombre: String,
    val precio: String,
    val preciorebajado: String,
    val imagenchica: String?
)

class ProductsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val idCategoria = intent.getStringExtra("idcategoria") ?: "1"
        val nombreCategoria = intent.getStringExtra("nombre") ?: "N/A"

        setContent {
            Sistema1228Theme {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        HeaderSection(idCategoria, nombreCategoria)

                        var productos by remember { mutableStateOf<List<Producto>>(emptyList()) }
                        var isLoading by remember { mutableStateOf(true) }
                        var errorMessage by remember { mutableStateOf<String?>(null) }

                        LaunchedEffect(idCategoria) {
                            loadProducts(idCategoria) { fetchedProducts, error ->
                                if (error != null) {
                                    errorMessage = error
                                } else {
                                    productos = fetchedProducts
                                }
                                isLoading = false
                            }
                        }

                        when {
                            isLoading -> LoadingIndicator()
                            errorMessage != null -> ErrorIndicator(errorMessage!!)
                            productos.isEmpty() -> NoProductsIndicator()
                            else -> ProductList(productos)
                        }
                    }
                }
            }
        }
    }

    private fun loadProducts(idCategoria: String, onResult: (List<Producto>, String?) -> Unit) {
        val url = "https://servicios.campus.pe/productos.php?idcategoria=$idCategoria"
        val requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                val productosList = mutableListOf<Producto>()
                for (i in 0 until response.length()) {
                    try {
                        val productoJson: JSONObject = response.getJSONObject(i)
                        val producto = Producto(
                            idproducto = productoJson.getString("idproducto"),
                            nombre = productoJson.getString("nombre"),
                            precio = productoJson.getString("precio"),
                            preciorebajado = productoJson.getString("preciorebajado"),
                            imagenchica = productoJson.optString("imagenchica", null)
                        )
                        productosList.add(producto)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                onResult(productosList, null)
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                onResult(emptyList(), "Error al cargar los productos.")
            }
        )

        requestQueue.add(jsonArrayRequest)
    }

    @Composable
    fun HeaderSection(idCategoria: String, nombreCategoria: String) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Category ID: $idCategoria", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Category Name: $nombreCategoria", style = MaterialTheme.typography.titleMedium)
        }
    }

    @Composable
    fun LoadingIndicator() {
        Text(text = "Loading products...", modifier = Modifier.padding(16.dp))
    }

    @Composable
    fun ErrorIndicator(message: String) {
        Text(text = message, modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.error)
    }

    @Composable
    fun NoProductsIndicator() {
        Text(text = "No products found for this category.", modifier = Modifier.padding(16.dp))
    }

    @Composable
    fun ProductList(productos: List<Producto>) {
        LazyColumn {
            items(productos) { producto ->
                ProductCard(producto)
            }
        }
    }

    @Composable
    fun ProductCard(producto: Producto) {
        // Manejo de la URL de la imagen
        val rutaFoto = if (producto.imagenchica.isNullOrEmpty()) {
            "https://servicios.campus.pe/imagenes/nofoto.jpg" // URL para imagen no encontrada
        } else {
            "https://servicios.campus.pe/${producto.imagenchica}" // URL de la imagen del producto
        }

        // Tarjeta del producto
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White), // Fondo blanco
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Nombre del producto
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleLarge
                )

                // Manejo de precios
                if (producto.preciorebajado.isNotEmpty()) {
                    Text(
                        text = "Precio: \$${producto.precio}",
                        style = MaterialTheme.typography.bodyMedium,
                        textDecoration = TextDecoration.LineThrough // Tachar el precio original
                    )
                    Text(
                        text = "Precio Rebajado: \$${producto.preciorebajado}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary) // Resaltar el precio rebajado
                    )
                } else {
                    Text(text = "Precio: \$${producto.precio}")
                }

                // Imagen del producto
                Image(
                    painter = rememberAsyncImagePainter(rutaFoto),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        }
    }

}

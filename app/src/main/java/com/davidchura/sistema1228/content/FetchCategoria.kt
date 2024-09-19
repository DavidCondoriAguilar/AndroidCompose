// Network.kt
package com.davidchura.sistema1228.content

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

fun fetchCategorias(context: Context, onResult: (List<Categorias>) -> Unit) {
    val url = "https://servicios.campus.pe/categorias.php"
    val requestQueue = Volley.newRequestQueue(context)

    val jsonArrayRequest = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        Response.Listener { response ->
            val categoriasList = mutableListOf<Categorias>()
            for (i in 0 until response.length()) {
                try {
                    val categoriaJson: JSONObject = response.getJSONObject(i)
                    val categoria = Categorias(
                        idcategoria = categoriaJson.getString("idcategoria"),
                        nombre = categoriaJson.getString("nombre"),
                        descripcion = categoriaJson.getString("descripcion"),
                        foto = categoriaJson.getString("foto"),
                        total = categoriaJson.getString("total")
                    )
                    categoriasList.add(categoria)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            onResult(categoriasList)
        },
        Response.ErrorListener { error ->
            error.printStackTrace()
            onResult(emptyList()) // En caso de error, devuelve una lista vacía
        }
    )

    requestQueue.add(jsonArrayRequest)
}

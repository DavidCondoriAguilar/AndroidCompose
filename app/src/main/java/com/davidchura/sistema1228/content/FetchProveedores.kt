// Network.kt
package com.davidchura.sistema1228.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.davidchura.sistema1228.content.Proveedor
import org.json.JSONException
import org.json.JSONObject

fun fetchProveedores(context: Context, onResult: (List<Proveedor>) -> Unit) {
    val url = "https://servicios.campus.pe/proveedores.php"
    val requestQueue = Volley.newRequestQueue(context)

    val jsonArrayRequest = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        Response.Listener { response ->
            val proveedoresList = mutableListOf<Proveedor>()
            for (i in 0 until response.length()) {
                try {
                    val proveedorJson: JSONObject = response.getJSONObject(i)
                    val proveedor = Proveedor(
                        nombreEmpresa = proveedorJson.getString("nombreempresa"),
                        nombreContacto = proveedorJson.getString("nombrecontacto"),
                        cargoContacto = proveedorJson.getString("cargocontacto"),
                        ciudad = proveedorJson.getString("ciudad"),
                        pais = proveedorJson.getString("pais"),
                        telefono = proveedorJson.getString("telefono"),
                        fax = proveedorJson.optString("fax", null)
                    )
                    proveedoresList.add(proveedor)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            onResult(proveedoresList)
        },
        Response.ErrorListener { error ->
            error.printStackTrace()
            onResult(emptyList()) // En caso de error, devuelve una lista vacía
        }
    )

    requestQueue.add(jsonArrayRequest)
}

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.davidchura.sistema1228.content.Empleado
import org.json.JSONException
import org.json.JSONObject

fun fetchEmpleados(context: Context, onResult: (List<Empleado>) -> Unit) {
    val url = "https://servicios.campus.pe/empleados.php"
    val requestQueue = Volley.newRequestQueue(context)

    val jsonArrayRequest = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        Response.Listener { response ->
            val empleadosList = mutableListOf<Empleado>()
            for (i in 0 until response.length()) {
                try {
                    val empleadoJson: JSONObject = response.getJSONObject(i)
                    val empleado = Empleado(
                        idEmpleado = empleadoJson.getString("idempleado"),
                        apellidos = empleadoJson.getString("apellidos"),
                        nombres = empleadoJson.getString("nombres"),
                        cargo = empleadoJson.getString("cargo"),
                        tratamiento = empleadoJson.getString("tratamiento"),
                        fechaNacimiento = empleadoJson.getString("fechanacimiento"),
                        fechaContratacion = empleadoJson.getString("fechacontratacion"),
                        direccion = empleadoJson.getString("direccion"),
                        ciudad = empleadoJson.getString("ciudad"),
                        usuario = empleadoJson.getString("usuario"),
                        codigoPostal = empleadoJson.getString("codigopostal"),
                        pais = empleadoJson.getString("pais"),
                        telefono = empleadoJson.getString("telefono"),
                        clave = empleadoJson.getString("clave"),
                        foto = empleadoJson.getString("foto"),
                        jefe = empleadoJson.getString("jefe")
                    )
                    empleadosList.add(empleado)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            onResult(empleadosList)
        },
        Response.ErrorListener { error ->
            error.printStackTrace()
            onResult(emptyList()) // Devuelve lista vacía en caso de error
        }
    )

    requestQueue.add(jsonArrayRequest)
}

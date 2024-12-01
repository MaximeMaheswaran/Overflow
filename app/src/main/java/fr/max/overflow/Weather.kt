package fr.max.overflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.widget.Toast
import org.json.JSONObject
import java.net.URL

class Weather() {

    suspend fun useApi(): String? {
        return withContext(Dispatchers.IO) { // Déplace l'appel réseau sur le thread IO
            try {
                URL("https://api.openweathermap.org/data/2.5/weather?q=paris&units=metrics&appid=cac4e55d825dba7489b081e6326503d1").readText()
            } catch (e: Exception) {
                null // Retourne null en cas d'erreur
            }
        }
    }

    fun getWeather(reponse: String?) : String? {
        try {
            /* Extracting JSON returns from the API */
            val jsonObj = JSONObject(reponse)
            val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
            val weatherMain = weather.getString("main")
            return weatherMain
        } catch (e: Exception) {
            return "null"
        }

    }


}
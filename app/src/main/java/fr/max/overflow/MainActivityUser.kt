package fr.max.overflow
import java.util.Date
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivityUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // set layout
        setContentView(R.layout.activity_main_user)
        // verification user connected or not
        verifUser()
        // time
        startClock()
        // set text for user
        setTextUser()
        //nav bar
        navBarListner()


    }

    // Fonction pour vérifier l'utilisateur
    private fun verifUser() {
        val user = intent.getIntExtra("user", 0)
        if (user == 0) {
            loadActivity(MainActivityLogin(), user, "")
        }
    }

    // Fonction pour mettre à jour le texte de l'utilisateur
    private fun setTextUser() {
        val username = intent.getStringExtra("username")
        val textView = findViewById<TextView>(R.id.user_text_view)
        textView.text = buildString {
            append("Bonjour ")
            append(username)
        }
    }

    // Fonction pour charger une nouvelle activité
    private fun loadActivity(activity: AppCompatActivity, user: Int, username: String) {
        val intent = Intent(this, activity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("username", username)
        onStop()
        startActivity(intent)
    }

    // Fonction pour gérer les clics sur la navigation de la barre de navigation
    private fun navBarListner() {
        // Ecouter la bar de navigation
        val user = intent.getIntExtra("user", 0)
        val username = intent.getStringExtra("username")
        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.selectedItemId = R.id.action_nav_user
        navBar.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.action_nav_home -> {
                    loadActivity(MainActivity(),user,username.toString())
                    return@setOnItemSelectedListener true
                }

                R.id.action_nav_user ->{
                    loadActivity(MainActivityUser(),user,username.toString())
                    return@setOnItemSelectedListener true
                }

                R.id.action_nav_basket -> {
                    loadActivity(MainActivity(),user,username.toString())
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }

        }

    }

    // Fonction pour mettre à jour l'heure
    private fun startClock() {
        val timeTextView = findViewById<TextView>(R.id.id_app_timer)
        val handler = Handler(Looper.getMainLooper())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        // Mettre à jour l'heure toutes les secondes
        val updateTimeRunnable = object : Runnable {
            override fun run() {
                weather()
                val currentTime = timeFormat.format(Date())
                timeTextView.text = currentTime
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateTimeRunnable)
    }

    // Fonction pour obtenir la météo
    private fun weather() {
        val weatherClass = Weather()
        val weather = findViewById<TextView>(R.id.id_app_weather)
        // Utiliser une coroutine pour appeler useApi
        lifecycleScope.launch {
            val response = weatherClass.useApi()
            if (response.isNullOrEmpty()) {
                Toast.makeText(this@MainActivityUser, "Erreur de connexion", Toast.LENGTH_SHORT).show()
            } else {
                // Traiter la réponse ici
                val weatherMain = weatherClass.getWeather(response)
                weather.text = weatherMain
            }
        }
    }

    // Fonction appelée lorsque l'activité est détruite
    override fun onDestroy() {
        super.onDestroy()
        val handler = Handler(Looper.getMainLooper())
        // Arrêter les mises à jour lorsque l'activité est détruite
        handler.removeCallbacksAndMessages(null)
    }

}


package fr.max.overflow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val user = intent.getIntExtra("user", 0)
        val username = intent.getStringExtra("username")

        verifUser(user, username.toString())
        // time
        startClock()
        // nav bar
        navBarListner()
        //login password test
        buttonListner()

    }

    // Fonction pour gérer les clics sur le bouton de connexion
    private fun  buttonListner() {
        val button = findViewById<Button>(R.id.login_button)
        button.setOnClickListener {
            verifLogin()
        }
    }

    // Fonction pour vérifier le login
    private fun verifLogin() {
        val login = findViewById<EditText>(R.id.text_input_login_id)
        val password = findViewById<EditText>(R.id.text_input_login_password)
        if (login.text.toString() == "admin" && password.text.toString() == "admin") {
            loadActivity(MainActivity(), 1, login.text.toString())
        } else {
            Toast.makeText(this, "Champs Incorrecte", Toast.LENGTH_SHORT).show()
        }
    }

    // Fonction pour vérifier l'utilisateur
    private fun verifUser(user: Int, username: String) {
        if (user == 1) {
            val intent = Intent(this, MainActivityUser::class.java)
            intent.putExtra("user", user)
            intent.putExtra("username", username)
            onStop()
            startActivity(intent)
        } else {
            setContentView(R.layout.activity_main_login)
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
                Toast.makeText(this@MainActivityLogin, "Erreur de connexion", Toast.LENGTH_SHORT).show()
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
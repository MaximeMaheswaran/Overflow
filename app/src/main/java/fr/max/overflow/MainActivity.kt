package fr.max.overflow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.max.overflow.fragments.BasketFragment
import fr.max.overflow.fragments.HomeFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    object Singleton {
        val basketList = arrayListOf<BasketModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //user connected or not test
        val user = intent.getIntExtra("user", 0)
        val username = intent.getStringExtra("username")
        Toast.makeText(this, "user : $user", Toast.LENGTH_SHORT).show()
        //injecter le fragment dans la boite (fragment_container_home)
        loadFragment(HomeFragment(this))
        //time
        startClock()
        //nav bar
        if (username != null) {
            navBarListner(user, username)
        } else {
            navBarListner(user, "")
        }
    }

    // Fonction pour charger un fragment
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_home, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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
    private fun navBarListner(user: Int, username: String) {
        // Ecouter la bar de navigation

        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.selectedItemId = R.id.action_nav_home
        navBar.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.action_nav_home -> {
                    loadFragment(HomeFragment(this))
                    return@setOnItemSelectedListener true
                }

                R.id.action_nav_user ->{
                    loadActivity(MainActivityUser(),user,username)
                    return@setOnItemSelectedListener true
                }

                R.id.action_nav_basket -> {
                    loadFragment(BasketFragment(this))
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
                Toast.makeText(this@MainActivity, "Erreur de connexion", Toast.LENGTH_SHORT).show()
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


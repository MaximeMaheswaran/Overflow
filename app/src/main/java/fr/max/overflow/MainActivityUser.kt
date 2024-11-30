package fr.max.overflow
import java.util.Calendar;
import java.util.Date;
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global.putString
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    private fun verifUser() {
        val user = intent.getIntExtra("user", 0)
        if (user == 0) {
            loadActivity(MainActivityLogin(), user, "")
        }
    }

    private fun setTextUser() {
        val username = intent.getStringExtra("username")
        val textView = findViewById<TextView>(R.id.user_text_view)
        textView.text = buildString {
            append("Bonjour ")
            append(username)
        }
    }

    private fun loadActivity(activity: AppCompatActivity, user: Int, username: String) {
        val intent = Intent(this, activity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("username", username)
        onStop()
        startActivity(intent)
    }

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

                else -> {
                    return@setOnItemSelectedListener false
                }
            }

        }

    }

    private fun startClock() {
        val timeTextView = findViewById<TextView>(R.id.id_app_timer)
        val handler = Handler(Looper.getMainLooper())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        // Mettre à jour l'heure toutes les secondes
        val updateTimeRunnable = object : Runnable {
            override fun run() {
                val currentTime = timeFormat.format(Date())
                timeTextView.text = currentTime
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateTimeRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        val handler = Handler(Looper.getMainLooper())
        // Arrêter les mises à jour lorsque l'activité est détruite
        handler.removeCallbacksAndMessages(null)
    }

}


package fr.max.overflow

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        //login password test
        val login = findViewById<EditText>(R.id.textinput_login_id)
        val password = findViewById<EditText>(R.id.textinput_login_password)
        val button = findViewById<Button>(R.id.login_button)

        button.setOnClickListener {
             if (login.text.toString() == "admin" && password.text.toString() == "admin") {
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
             }
        }
    }
}
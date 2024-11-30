package fr.max.overflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.max.overflow.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }*/
        /*//charger les items
        val repo = ItemRepository()

        //mettre a jour la list des items
        repo.updateData{
            //injecter le fragment dans la boite (fragment_container_home)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_home, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
            println("test")
        }*/

        //injecter le fragment dans la boite (fragment_container_home)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_home, HomeFragment(this))
        transaction.addToBackStack(null)
        transaction.commit()



        // Ecouter la bar de navigation

        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.action_nav_home -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container_home, HomeFragment(this))
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return@setOnItemSelectedListener true
                }

                R.id.action_nav_user ->{
                    onStop()
                    val intent = Intent(this, MainActivityUser::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }

        }

    }
}
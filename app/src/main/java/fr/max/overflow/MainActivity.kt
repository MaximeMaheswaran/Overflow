package fr.max.overflow

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

    }
}
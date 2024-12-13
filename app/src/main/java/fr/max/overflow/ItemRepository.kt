package fr.max.overflow

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.max.overflow.ItemRepository.Singleton.databaseRef
import fr.max.overflow.ItemRepository.Singleton.itemsList

class ItemRepository {

    object Singleton {

        //se connecter a la reference "Items"
        val databaseRef = FirebaseDatabase.getInstance().getReference("Items")

        // creer une liste qui va contenire les items
        val itemsList = arrayListOf<ItemModel>()
    }


    fun updateData(callback: ()->Unit) {
        // absorber les donnée depuis la base de donnée
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // retirer les anciennes items
                itemsList.clear()
                //recolter la list
                for (element in snapshot.children) {
                    // construire un objet item
                    val item = element.getValue(ItemModel::class.java)
                    if (item != null) {
                        itemsList.add(item)

                    }
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")

            }
        })
    }



}
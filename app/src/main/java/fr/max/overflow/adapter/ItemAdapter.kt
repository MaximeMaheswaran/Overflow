package fr.max.overflow.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.max.overflow.ItemModel
import fr.max.overflow.ItemPopup
import fr.max.overflow.MainActivity
import fr.max.overflow.R

class ItemAdapter(val context : MainActivity, private val unItemsList: List<ItemModel>, private val unLayoutId: Int) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    //Boite pour ranger toute les composants a contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemImage : ImageView? = view.findViewById<ImageView>(R.id.card_view_item_image)
        val itemName = view.findViewById<TextView>(R.id.card_view_item_name)
        val itemDescription = view.findViewById<TextView>(R.id.card_view_item_description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(unLayoutId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return unItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //mettre a jour les information de l'item
        val currentItem = unItemsList[position]


        // Convertir le nom de l'image en identifiant de ressource
        val imageResId = context.resources.getIdentifier(currentItem.imageId, "drawable", context.packageName)
        //mettre a jour l'image
        holder.itemImage?.setImageResource(imageResId)
        //mettre a jour le nom et la description du produit
        holder.itemName.text = currentItem.name
        holder.itemDescription.text = currentItem.description

        //ecouter les items
        holder.itemView.setOnClickListener {
            //afficher les details du produit
            ItemPopup(this, currentItem).show()

            /*val intent = Intent(context, MainActivityItem::class.java)
            context.startActivity(intent)*/

        }

    }
}
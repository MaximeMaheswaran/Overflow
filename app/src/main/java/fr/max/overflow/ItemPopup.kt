package fr.max.overflow

import android.app.Dialog
import android.os.Bundle
import fr.max.overflow.adapter.ItemAdapter
import android.view.Window
import android.widget.ImageView
import android.widget.TextView

class ItemPopup(private val adapter: ItemAdapter, private val currentItem: ItemModel) : Dialog(adapter.context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // pour dire q'on ne veut pas de titre
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_item_detail)
        setupComponents()
    }

    private fun setupComponents() {
        //actualiser l'image du popup
        val itemImage = findViewById<ImageView>(R.id.popup_item_image)
        // Convertir le nom de l'image en identifiant de ressource
        val imageResId = context.resources.getIdentifier(currentItem.imageId, "drawable", context.packageName)
        //mettre a jour l'image
        itemImage.setImageResource(imageResId)


        //actualiser le nom du produit
        findViewById<TextView>(R.id.popup_item_name).text = currentItem.name

        //actualiser la description du produit
        findViewById<TextView>(R.id.popup_item_description).text = currentItem.description

        //actualiser la quantité du produit
        findViewById<TextView>(R.id.popup_item_quantity).text = currentItem.qte.toString()

        //actualiser le prix du produit
        findViewById<TextView>(R.id.popup_item_price).text = currentItem.price.toString()



    }

}
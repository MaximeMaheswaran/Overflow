package fr.max.overflow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.max.overflow.ItemModel
import fr.max.overflow.ItemRepository.Singleton.itemsList
import fr.max.overflow.MainActivity
import fr.max.overflow.R
import fr.max.overflow.adapter.ItemAdapter
import fr.max.overflow.adapter.ItemsDecorations

class HomeFragment(private val context : MainActivity) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val itemsList = arrayListOf<ItemModel>()

        itemsList.add(ItemModel(
            1,
            "CALVIN KLEIN",
            "Sweat Crewneck BEH 6610 Noir",
            "calvin_klein_white_sweat_01",
            90,
            50
        ))
        itemsList.add(ItemModel(
            2,
            "CALVIN KLEIN",
            "Robe Pull Femme 6740 Noir",
            "calvin_klein_black_01",
            105,
            42
        ))
        itemsList.add(ItemModel(
            3,
            "TEDDY YACHT CLUB",
            "Sweat Capuche Maison De Couture Art Edition Blanc",
            "teddy_yacht_club_01",
            120,
            48
        ))



        // recuperer le recyclerview
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = ItemAdapter(context, itemsList, R.layout.item_vertical)
        verticalRecyclerView?.addItemDecoration(ItemsDecorations())



        return view
    }
}
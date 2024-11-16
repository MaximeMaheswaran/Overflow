package fr.max.overflow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.max.overflow.ItemModel
import fr.max.overflow.MainActivity
import fr.max.overflow.R
import fr.max.overflow.adapter.ItemAdapter
import fr.max.overflow.adapter.ItemsDecorations

class HomeFragment(private val context : MainActivity) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        val itemsList = arrayListOf<ItemModel>()

        itemsList.add(ItemModel())



        // recuperer le recyclerview
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = ItemAdapter(context, itemsList, R.layout.item_vertical)
        verticalRecyclerView?.addItemDecoration(ItemsDecorations())



        return view
    }
}
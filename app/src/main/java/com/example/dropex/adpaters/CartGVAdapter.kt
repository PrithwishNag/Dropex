package com.example.dropex.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.dropex.R
import com.example.dropex.models.CartModel
import java.util.Locale

class CartGVAdapter(context: Context, courseModelArrayList: ArrayList<CartModel?>?) :
    ArrayAdapter<CartModel?>(context, 0, courseModelArrayList!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        }
        val cartModel = getItem(position)
        val productTVName = listItemView!!.findViewById<TextView>(R.id.productNameTV)
        val productImageIV = listItemView.findViewById<ImageView>(R.id.productImageIV)
        listItemView.findViewById<View>(R.id.quantitySetter) // To load quantitySetter
        val quantityTV = listItemView.findViewById<TextView>(R.id.quantityTV)
        val sizeTV = listItemView.findViewById<TextView>(R.id.sizeTV)
        val totalPriceTV = listItemView.findViewById<TextView>(R.id.totalPriceTV)
        val sizeText = "Size: " + cartModel!!.size.toString().uppercase(Locale.getDefault())
        productTVName.setText(R.string.placeholder)
        productImageIV.setImageResource(R.drawable.sample)
        quantityTV.text = cartModel.quantity.toString()
        sizeTV.text = sizeText
        totalPriceTV.text = "$3232"
        return listItemView
    }
}
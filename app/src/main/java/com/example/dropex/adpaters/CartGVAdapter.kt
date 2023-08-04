package com.example.dropex.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.dropex.R
import com.example.dropex.models.CartModel
import com.example.dropex.models.ProductModel
import java.util.Locale

class CartGVAdapter(context: Context, courseModelArrayList: ArrayList<CartModel?>?, private var productModels: ArrayList<ProductModel?>) : ArrayAdapter<CartModel?>(context, 0, courseModelArrayList!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        }

        listItemView!!.findViewById<TextView>(R.id.closeBtnTV).setOnClickListener {
            // Close button
        }

        val cartModel = getItem(position)
        val productModel = productModels[position]
        val productTVName = listItemView.findViewById<TextView>(R.id.productNameTV)
        val productImageIV = listItemView.findViewById<ImageView>(R.id.productImageIV)
        val quantitySetter = listItemView.findViewById<View>(R.id.quantitySetter)
        val quantityTV = quantitySetter.findViewById<TextView>(R.id.quantityTV)
        val sizeTV = listItemView.findViewById<TextView>(R.id.sizeTV)
        val totalPriceTV = listItemView.findViewById<TextView>(R.id.totalPriceTV)
        val sizeText = "Size: " + cartModel!!.size.uppercase(Locale.getDefault())

        productTVName.text = productModel!!.name
        productImageIV.load(productModel.imgUrl)
        totalPriceTV.text = "$" + cartModel.quantity * productModel.price
        quantityTV.text = cartModel.quantity.toString()
        sizeTV.text = sizeText
        return listItemView
    }
}
package com.example.dropex.adpaters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.dropex.R
import com.example.dropex.models.ProductModel
import com.example.dropex.screens.ProductDetailsScreen

class HomeGVAdapter(context: Context, courseModelArrayList: ArrayList<ProductModel?>?) :
    ArrayAdapter<ProductModel?>(context, 0, courseModelArrayList!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.card_product, parent, false)
        }
        listItemView!!.setOnClickListener {
            val intent = Intent(context, ProductDetailsScreen::class.java)
            context.startActivity(intent)
        }
        val productModel = getItem(position)
        val productTVName = listItemView.findViewById<TextView>(R.id.productNameTV)
        val productTVPrice = listItemView.findViewById<TextView>(R.id.productPriceTV)
        val productIVImage = listItemView.findViewById<ImageView>(R.id.productImageIV)
        productTVName.text = productModel!!.name
        productTVPrice.text = "$" + productModel.price
        productIVImage.setImageResource(productModel.imgId)
        return listItemView
    }
}
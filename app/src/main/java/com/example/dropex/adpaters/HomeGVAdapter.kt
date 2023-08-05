package com.example.dropex.adpaters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.dropex.R
import com.example.dropex.constants.Constants
import com.example.dropex.models.ProductModel
import com.example.dropex.screens.ProductDetailsScreen

class HomeGVAdapter(context: Context, private var productIds: List<String?>, courseModelArrayList: List<ProductModel?>?) : ArrayAdapter<ProductModel?>(context, 0, courseModelArrayList!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.card_product, parent, false)
        }
        listItemView!!.setOnClickListener {
            val intent = Intent(context, ProductDetailsScreen::class.java)
            intent.putExtra(Constants.PRODUCT_ID_EXTRA, productIds[position])
            context.startActivity(intent)
        }
        val productModel = getItem(position)
        val productTVName = listItemView.findViewById<TextView>(R.id.productNameTV)
        val productTVPrice = listItemView.findViewById<TextView>(R.id.productPriceTV)
        val productIVImage = listItemView.findViewById<ImageView>(R.id.productImageIV)
        productTVName.text = productModel!!.name
        productTVPrice.text = "$" + productModel.price
        productIVImage.load(productModel.imgUrl)
        return listItemView
    }
}
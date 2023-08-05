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
import com.example.dropex.models.OrdersModel
import com.example.dropex.models.ProductModel
import java.util.Locale

class OrderGVAdapter(context: Context, ordersModelArrayList: ArrayList<OrdersModel?>, private var productModelArrayList: ArrayList<ProductModel?>) : ArrayAdapter<OrdersModel?>(context, 0, ordersModelArrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false)
        }

        val orderModel = getItem(position)
        val productModel = productModelArrayList[position]
        val productTVName = listItemView!!.findViewById<TextView>(R.id.productNameTV)
        val productImageIV = listItemView.findViewById<ImageView>(R.id.productImageIV)
        val quantityTV = listItemView.findViewById<TextView>(R.id.quantityTV)
        val sizeTV = listItemView.findViewById<TextView>(R.id.sizeTV)
        val totalPriceTV = listItemView.findViewById<TextView>(R.id.totalPriceTV)

        orderModel!!
        productModel!!

        val priceText = "$" + orderModel.quantity * productModel.price
        val sizeText = "Size: " + orderModel.size.uppercase(Locale.getDefault())
        val quantityText = "Quantity: " + orderModel.quantity.toString().uppercase(Locale.getDefault())

        productTVName.text = productModel.name
        productImageIV.load(productModel.imgUrl)
        totalPriceTV.text = priceText
        quantityTV.text = quantityText
        sizeTV.text = sizeText
        return listItemView
    }
}
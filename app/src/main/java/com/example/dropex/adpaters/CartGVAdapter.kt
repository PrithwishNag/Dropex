package com.example.dropex.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import coil.load
import com.example.dropex.R
import com.example.dropex.database.CartRepository
import com.example.dropex.models.CartModel
import com.example.dropex.models.ProductModel
import java.util.Locale

class CartGVAdapter(context: Context, private var confirmed: Boolean, private var cartModelArrayList: ArrayList<CartModel?>, private var productModelArrayList: ArrayList<ProductModel?>) : ArrayAdapter<CartModel?>(context, 0, cartModelArrayList) {
    private var cartRepository = CartRepository()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        }

        listItemView!!

        val cartModel = getItem(position)
        val productModel = productModelArrayList[position]
        val productTVName = listItemView.findViewById<TextView>(R.id.productNameTV)
        val productImageIV = listItemView.findViewById<ImageView>(R.id.productImageIV)
        val quantityTV = listItemView.findViewById<TextView>(R.id.quantityTV)
        val sizeTV = listItemView.findViewById<TextView>(R.id.sizeTV)
        val totalPriceTV = listItemView.findViewById<TextView>(R.id.totalPriceTV)
        val closeBtn = listItemView.findViewById<ImageView>(R.id.closeBtnIV)

        cartModel!!
        productModel!!

        if (confirmed) {
            closeBtn.visibility = View.GONE
        } else {
            closeBtn.setOnClickListener {
                // Close button
                cartRepository.removeFromCart(cartModel.productId)
                cartModelArrayList.removeAt(position)
                productModelArrayList.removeAt(position)
                notifyDataSetChanged()
                Toast.makeText(context, "Cart item removed", Toast.LENGTH_SHORT).show()
            }
        }

        val priceText = "$" + cartModel.quantity * productModel.price
        val sizeText = "Size: " + cartModel.size.uppercase(Locale.getDefault())
        val quantityText = "Quantity: " + cartModel.quantity.toString().uppercase(Locale.getDefault())

        productTVName.text = productModel.name
        productImageIV.load(productModel.imgUrl)
        totalPriceTV.text = priceText
        quantityTV.text = quantityText
        sizeTV.text = sizeText
        return listItemView
    }
}
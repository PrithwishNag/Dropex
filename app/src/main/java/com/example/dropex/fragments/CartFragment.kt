package com.example.dropex.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dropex.R
import com.example.dropex.adpaters.CartGVAdapter
import com.example.dropex.database.CartRepository
import com.example.dropex.database.ProductsRepository
import com.example.dropex.models.ProductModel
import com.example.dropex.screens.PaymentGatewayScreen
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private lateinit var cartGV: GridView
    private var cartRepository: CartRepository = CartRepository()
    private var productsRepository: ProductsRepository = ProductsRepository()
    private var isCartEmpty: Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cartGV = view.findViewById(R.id.cartGV)
        val loadingBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val totalPriceTV = view.findViewById<TextView>(R.id.totalPriceTV)
        val checkoutBtn = view.findViewById<MaterialButton>(R.id.checkoutBtn)
        val noItemsFoundTV = view.findViewById<TextView>(R.id.noItemsFoundTV)

        checkoutBtn.setOnClickListener {
            if (isCartEmpty == null) return@setOnClickListener
            if (!isCartEmpty!!) {
                val intent = Intent(context, PaymentGatewayScreen::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show()
            }
        }

        cartGV.visibility = View.GONE
        loadingBar.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            val cartModelArrayList = cartRepository.getCartItems()
            if (cartModelArrayList.size == 0) {
                isCartEmpty = true
                loadingBar.visibility = View.GONE
                noItemsFoundTV.visibility = View.VISIBLE
                return@launch
            } else {
                isCartEmpty = false
            }
            val productModelArrayList = ArrayList<ProductModel?>()
            for (cartModel in cartModelArrayList) {
                productModelArrayList.add(productsRepository.getProductById(cartModel!!.productId))
            }
            var totalPrice = 0
            for (i in 0 until productModelArrayList.size) {
                totalPrice += cartModelArrayList[i]!!.quantity * productModelArrayList[i]!!.price
            }
            val adapter = CartGVAdapter(view.context, false, cartModelArrayList, productModelArrayList)
            cartGV.adapter = adapter

            cartGV.visibility = View.VISIBLE
            loadingBar.visibility = View.GONE
            noItemsFoundTV.visibility = View.GONE
            totalPriceTV.text = "$" + totalPrice
        }
    }
}
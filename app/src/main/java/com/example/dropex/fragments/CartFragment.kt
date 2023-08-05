package com.example.dropex.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cartGV = view.findViewById(R.id.cartGV)
        val checkoutBtn = view.findViewById<MaterialButton>(R.id.checkoutBtn)

        checkoutBtn.setOnClickListener {
            val intent = Intent(context, PaymentGatewayScreen::class.java)
            startActivity(intent)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val cartModelArrayList = cartRepository.getCartItems()
            val productModels = ArrayList<ProductModel?>()
            for (cartModel in cartModelArrayList) {
                productModels.add(productsRepository.getProductById(cartModel!!.productId))
            }
            val adapter = CartGVAdapter(view.context, cartModelArrayList, productModels)
            cartGV.adapter = adapter
        }
    }
}
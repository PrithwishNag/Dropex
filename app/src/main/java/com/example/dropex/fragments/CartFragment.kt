package com.example.dropex.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.dropex.R
import com.example.dropex.adpaters.CartGVAdapter
import com.example.dropex.enums.Size
import com.example.dropex.models.CartModel
import com.example.dropex.screens.PaymentGatewayScreen
import com.google.android.material.button.MaterialButton

class CartFragment : Fragment() {

    private lateinit var cartGVAdapter: GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val checkoutBtn = view.findViewById<MaterialButton>(R.id.checkoutBtn)
        checkoutBtn.setOnClickListener { v: View? ->
            val intent = Intent(context, PaymentGatewayScreen::class.java)
            startActivity(intent)
        }
        cartGVAdapter = view.findViewById(R.id.cartGV)
        val cartModelArrayList = ArrayList<CartModel?>()
        cartModelArrayList.add(CartModel(2, Size.l.name, "1", "2"))
        cartModelArrayList.add(CartModel(1, Size.m.name, "1", "3"))
        cartModelArrayList.add(CartModel(1, Size.xxl.name, "1", "4"))
        val adapter = CartGVAdapter(view.context, cartModelArrayList)
        cartGVAdapter.adapter = adapter
    }
}
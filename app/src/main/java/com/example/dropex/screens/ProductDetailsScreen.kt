package com.example.dropex.screens

import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.dropex.R
import com.example.dropex.constants.Constants
import com.example.dropex.database.CartRepository
import com.example.dropex.database.ProductsRepository
import com.google.android.material.button.MaterialButton

class ProductDetailsScreen : AppCompatActivity() {
    private lateinit var productNameTV: TextView
    private lateinit var productPriceTV: TextView
    private lateinit var productImageIV: ImageView
    private var productsRepository = ProductsRepository()
    private var cartRepository = CartRepository()
    private lateinit var productId: String
    private var quantity: Int = 1

    private fun initiate() {
        productNameTV = findViewById(R.id.productNameTV)
        productPriceTV = findViewById(R.id.productPriceTV)
        productImageIV = findViewById(R.id.productImageIV)
        productId = intent.extras?.getString(Constants.PRODUCT_ID_EXTRA).toString()
    }

    private fun initQuantitySetter() {
        val quantitySetter = findViewById<View>(R.id.quantitySetter)
        val quantityTV = quantitySetter.findViewById<TextView>(R.id.quantityTV)
        val quantityMinus = quantitySetter.findViewById<ImageView>(R.id.quantityMinus)
        quantityMinus.setOnClickListener {
            if (quantity == 0) return@setOnClickListener
            --quantity
            quantityTV.text = quantity.toString()
        }
        val quantityPlus = quantitySetter.findViewById<ImageView>(R.id.quantityPlus)
        quantityPlus.setOnClickListener {
            if (quantity == 5) return@setOnClickListener
            ++quantity
            quantityTV.text = quantity.toString()
        }
    }

    fun initAddToCart() {
        val addToCartBtn = findViewById<MaterialButton>(R.id.addToCartBtn)
        val productSize = findViewById<HorizontalScrollView>(R.id.productSize)
        val sizeRG = productSize.findViewById<RadioGroup>(R.id.sizeRG)
        addToCartBtn.setOnClickListener {
            val radioButton = findViewById<RadioButton>(sizeRG.checkedRadioButtonId)
            cartRepository.addToCart(quantity = quantity, size = radioButton.text.toString(), productId = productId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details_screen)

        initiate()
        initQuantitySetter()
        initAddToCart()

        productsRepository.getProductById(productId).addOnSuccessListener {
            productNameTV.text = it.child("name").value.toString()
            productPriceTV.text = "$" + it.child("price").value.toString()
            productImageIV.load(it.child("imgUrl").value.toString())
        }
    }
}
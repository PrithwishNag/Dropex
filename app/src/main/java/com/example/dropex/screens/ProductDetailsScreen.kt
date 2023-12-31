package com.example.dropex.screens

import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.dropex.R
import com.example.dropex.constants.Constants
import com.example.dropex.database.CartRepository
import com.example.dropex.database.ProductsRepository
import com.example.dropex.database.WishlistRepository
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class ProductDetailsScreen : AppCompatActivity() {
    private lateinit var productNameTV: TextView
    private lateinit var productPriceTV: TextView
    private lateinit var productImageIV: ImageView
    private var productsRepository = ProductsRepository()
    private var cartRepository = CartRepository()
    private var wishlistRepository = WishlistRepository()
    private lateinit var wishlistBtnCard: CardView
    private lateinit var productId: String
    private var quantity: Int = 1
    private var isWishlisted: Boolean = false

    private fun initiate() {
        productNameTV = findViewById(R.id.productNameTV)
        productPriceTV = findViewById(R.id.productPriceTV)
        productImageIV = findViewById(R.id.productImageIV)
        wishlistBtnCard = findViewById(R.id.wishlistBtnCard)
        productId = intent.extras?.getString(Constants.PRODUCT_ID_EXTRA).toString()
    }

    private fun initWishlist() {
        lifecycleScope.launch {
            isWishlisted = wishlistRepository.isWishlisted(productId)
            val wishlistImageIV = wishlistBtnCard.findViewById<ImageView>(R.id.wishlistImageIV)
            if (!isWishlisted) {
                wishlistImageIV.setImageResource(R.drawable.icons_heart)
            } else {
                wishlistImageIV.setImageResource(R.drawable.icons_heart_filled)
            }
            wishlistBtnCard.setOnClickListener {
                isWishlisted = if (isWishlisted) {
                    wishlistImageIV.setImageResource(R.drawable.icons_heart)
                    wishlistRepository.removeFromWishlist(productId)
                    false
                } else {
                    wishlistImageIV.setImageResource(R.drawable.icons_heart_filled)
                    wishlistRepository.addToWishlist(productId)
                    true
                }
            }
        }
    }

    private fun initQuantitySetter() {
        val quantitySetter = findViewById<View>(R.id.quantitySetter)
        val quantityTV = quantitySetter.findViewById<TextView>(R.id.quantityTV)
        val quantityMinus = quantitySetter.findViewById<ImageView>(R.id.quantityMinus)
        quantityMinus.setOnClickListener {
            if (quantity == 1) return@setOnClickListener
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

    private fun initAddToCart() {
        val addToCartBtn = findViewById<MaterialButton>(R.id.addToCartBtn)
        val productSize = findViewById<HorizontalScrollView>(R.id.productSize)
        val sizeRG = productSize.findViewById<RadioGroup>(R.id.sizeRG)
        addToCartBtn.setOnClickListener {
            val radioButton = findViewById<RadioButton>(sizeRG.checkedRadioButtonId)
            cartRepository.addToCart(quantity = quantity, size = radioButton.text.toString(), productId = productId)
            Toast.makeText(applicationContext, "Added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details_screen)

        initiate()
        initQuantitySetter()
        initAddToCart()
        initWishlist()

        lifecycleScope.launch {
            val productModel = productsRepository.getProductById(productId)
            productNameTV.text = productModel.name
            productPriceTV.text = "$" + productModel.price.toString()
            productImageIV.load(productModel.imgUrl)
        }
    }
}
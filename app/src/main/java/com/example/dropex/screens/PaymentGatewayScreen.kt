package com.example.dropex.screens

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dropex.R
import com.example.dropex.adpaters.CartGVAdapter
import com.example.dropex.constants.Constants
import com.example.dropex.customComponents.WrappingGridView
import com.example.dropex.database.CartRepository
import com.example.dropex.database.OrdersRepository
import com.example.dropex.database.ProductsRepository
import com.example.dropex.database.UserRepository
import com.example.dropex.enums.HomeScreenTabs
import com.example.dropex.models.ProductModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class PaymentGatewayScreen : AppCompatActivity() {
    private lateinit var cartGV: WrappingGridView
    private lateinit var payBtn: MaterialButton
    private var userRepository: UserRepository = UserRepository()
    private var cartRepository: CartRepository = CartRepository()
    private var productsRepository: ProductsRepository = ProductsRepository()
    private var ordersRepository: OrdersRepository = OrdersRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_gateway_screen)
        cartGV = findViewById(R.id.summaryItemsGV)
        payBtn = findViewById(R.id.payBtn)

        lifecycleScope.launch {
            val cartModelArrayList = cartRepository.getCartItems()
            val userModel = userRepository.getCurrentUser()

            val addressTV = findViewById<TextView>(R.id.addressTV)
            addressTV.text = userModel.address

            val productModels = ArrayList<ProductModel?>()
            for (cartModel in cartModelArrayList) {
                productModels.add(productsRepository.getProductById(cartModel!!.productId))
            }
            val adapter = CartGVAdapter(applicationContext, true, cartModelArrayList, productModels)
            cartGV.adapter = adapter

            payBtn.setOnClickListener {
                for (cartModel in cartModelArrayList) {
                    ordersRepository.placeOrder(cartModel!!.productId, cartModel.quantity, cartModel.size)
                    cartRepository.removeFromCart(cartModel.productId)
                }
                val intent = Intent(applicationContext, HomeScreen::class.java)
                intent.putExtra(Constants.HOME_SCREEN_TAB_EXTRA, HomeScreenTabs.orders.name)
                startActivity(intent)
                finish()
                Toast.makeText(applicationContext, "Order placed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
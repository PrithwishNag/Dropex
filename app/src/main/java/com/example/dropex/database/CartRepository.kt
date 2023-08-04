package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.CartModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.tasks.await

class CartRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference
    private var mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun addToCart(productId: String, quantity: Int, size: String) {
        val cartModel = CartModel(quantity = quantity, size = size, userId = mUser!!.uid, productId = productId)
        mDatabase.child("cart").push().setValue(cartModel)
    }

    suspend fun getCartItems(): ArrayList<CartModel?> {
        val data = mDatabase.child("cart").get().await()
        val cartItems = ArrayList<CartModel?>()
        for (cartItem in data.children) {
            cartItems.add(CartModel(
                    quantity = cartItem.child("quantity").value.toString().toInt(),
                    size = cartItem.child("size").value.toString(),
                    productId = cartItem.child("productId").value.toString(),
                    userId = cartItem.child("userId").value.toString()))
        }
        return cartItems
    }
}
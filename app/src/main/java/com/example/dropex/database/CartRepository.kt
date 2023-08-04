package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.CartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CartRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference
    private var mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun addToCart(productId: String, quantity: Int, size: String) {
        val cartModel = CartModel(
                quantity = quantity,
                size = size,
                userId = mUser!!.uid,
                productId = productId
        )
        mDatabase.child("cart").push().setValue(cartModel)
    }
}
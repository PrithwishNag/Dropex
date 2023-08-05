package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.OrdersModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class OrdersRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference
    private var mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun placeOrder(productId: String, quantity: Int, size: String) {
        val orderModel = OrdersModel(quantity = quantity, size = size, userId = mUser!!.uid, productId = productId)
        mDatabase.child("orders").push().setValue(orderModel)
    }

    suspend fun getOrders(): ArrayList<OrdersModel?> {
        val data = mDatabase.child("orders").orderByChild("userId").equalTo(mUser!!.uid).get().await()
        val orderItems = ArrayList<OrdersModel?>()
        for (cartItem in data.children) {
            orderItems.add(OrdersModel(quantity = cartItem.child("quantity").value.toString().toInt(), size = cartItem.child("size").value.toString(), productId = cartItem.child("productId").value.toString(), userId = cartItem.child("userId").value.toString()))
        }
        return orderItems
    }
}
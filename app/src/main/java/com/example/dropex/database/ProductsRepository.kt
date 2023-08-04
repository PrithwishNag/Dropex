package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.ProductModel
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class ProductsRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference

    fun getAllProducts(): Task<DataSnapshot> {
        return mDatabase.child("products").get()
    }

    suspend fun getProductById(id: String): ProductModel {
        val product = mDatabase.child("products").child(id).get().await()
        return ProductModel(
                name = product.child("name").value.toString(),
                price = product.child("price").value.toString().toInt(),
                imgUrl = product.child("imgUrl").value.toString()
        )
    }
}
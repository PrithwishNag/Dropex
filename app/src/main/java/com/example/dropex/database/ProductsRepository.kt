package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class ProductsRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference

    suspend fun getAllProducts(): Map<String, ProductModel?> {
        val data = mDatabase.child("products").get().await()
        val productsMap = HashMap<String, ProductModel?>()
        for (product in data.children) {
            val productModel = ProductModel(
                    name = product.child("name").value.toString(),
                    price = product.child("price").value.toString().toInt(),
                    imgUrl = product.child("imgUrl").value.toString()
            )
            productsMap[product.key.toString()] = productModel
        }
        return productsMap
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
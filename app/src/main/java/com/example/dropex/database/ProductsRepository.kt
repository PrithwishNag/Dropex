package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductsRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference

    fun getAllProducts(): Task<DataSnapshot> {
        return mDatabase.child("products").get()
    }

    fun getProductById(id: String): Task<DataSnapshot> {
        return mDatabase.child("products").child(id).get()
    }
}
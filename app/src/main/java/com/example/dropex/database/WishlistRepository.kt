package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.WishlistModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class WishlistRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference
    private var mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun addToWishlist(productId: String) {
        mDatabase.child("wishlist").push().setValue(WishlistModel(userId = mUser!!.uid, productId = productId))
    }

    fun removeFromWishlist(productId: String) {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataSnapshot1 in dataSnapshot.children) {
                    if (dataSnapshot1.child("userId").exists() && dataSnapshot1.child("productId").exists()) {
                        if (dataSnapshot1.child("userId").value.toString() == mUser!!.uid && dataSnapshot1.child("productId").value.toString() == productId) {
                            dataSnapshot1.ref.removeValue()
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        mDatabase.child("wishlist").addListenerForSingleValueEvent(listener)
    }

    suspend fun getWishlistItems(): ArrayList<WishlistModel?> {
        val data = mDatabase.child("wishlist").orderByChild("userId").equalTo(mUser!!.uid).get().await()
        val wishlistItems = ArrayList<WishlistModel?>()
        for (wishlistItem in data.children) {
            wishlistItems.add(WishlistModel(userId = mUser!!.uid, productId = wishlistItem.child("productId").value.toString()))
        }
        return wishlistItems
    }

    suspend fun isWishlisted(productId: String): Boolean {
        val data = mDatabase.child("wishlist").orderByChild("userId").equalTo(mUser!!.uid).ref.orderByChild("productId").equalTo(productId).get().await()
        return (data.value != null)
    }
}
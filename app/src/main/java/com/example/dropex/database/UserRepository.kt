package com.example.dropex.database

import com.example.dropex.constants.Constants
import com.example.dropex.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class UserRepository {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance(Constants.DATABASE_URL).reference
    private var mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun createNewUser(mUser: FirebaseUser, user: UserModel) {
        mDatabase.child("users").child(mUser.uid).setValue(user)
    }

    suspend fun getCurrentUser(): UserModel {
        val dataSnapshot = mDatabase.child("users").child(mUser!!.uid).get().await()
        return UserModel(
                name = dataSnapshot.child("name").value.toString(),
                email = dataSnapshot.child("email").value.toString(),
                address = dataSnapshot.child("address").value.toString(),
        )
    }
}
package com.example.projemanag.firebase

import com.example.projemanag.activities.MainActivity
import com.example.projemanag.activities.SignInActivity
import com.example.projemanag.activities.SignUpActivity
import com.example.projemanag.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreClass {
    private val mFirestoreClass = Firebase.firestore;

    fun registerUser(activity: SignUpActivity, userInfo: User) {
        mFirestoreClass.collection("User")
            .document(getUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.registeredSuccess()
            }
            .addOnFailureListener {

            }
    }
    fun signInUser(activity: SignInActivity) {
        mFirestoreClass.collection("User")
            .document(getUserId())
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)!!
                activity.singInSuccess(user)
            }
            .addOnFailureListener {

            }
    }
    fun getUserId(): String {
        return FirebaseAuth.getInstance().uid!!
    }
}
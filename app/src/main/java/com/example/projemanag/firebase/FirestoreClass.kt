package com.example.projemanag.firebase

import android.app.Activity
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
    fun signInUser(activity: Activity) {
        mFirestoreClass.collection("User")
            .document(getUserId())
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)!!
                when(activity) {
                    is SignInActivity -> {
                        activity.singInSuccess(user)
                    }
                    is MainActivity -> {
                        activity.loadUserNavigationData(user)
                    }
                }
            }
            .addOnFailureListener {

            }
    }
    fun getUserId(): String {
        var firebaseAuth = FirebaseAuth.getInstance()
        var currentUserId = ""
        if(firebaseAuth.uid != null) {
            currentUserId = firebaseAuth.uid!!
        }
        return currentUserId
    }
}
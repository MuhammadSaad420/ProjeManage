package com.example.projemanag.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignInBinding
import com.example.projemanag.firebase.FirestoreClass
import com.example.projemanag.models.User
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {
    var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        setSupportActionBar(binding?.toolbarSignInActivity)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        binding?.toolbarSignInActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnSignIn?.setOnClickListener {
            loginUser()
        }
    }
    private fun loginUser() {
        if(validateUser()) {
            val email = binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()
            showProgressDialog("Logging In...")
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        signInUser()
                    } else {
                        hideProgressDialog()
                        showErrorSnackBar(it.exception!!.message!!)
                    }
                }
        }
    }

    private fun signInUser() {
        FirestoreClass().signInUser(this@SignInActivity)
    }
    fun singInSuccess(user:User) {
        hideProgressDialog()
        Toast.makeText(this@SignInActivity,"User LoggedIn successfully",
            Toast.LENGTH_LONG).show()
        startActivity(Intent(this@SignInActivity,MainActivity:: class.java))
    }

    private fun validateUser(): Boolean {
        return when {
            TextUtils.isEmpty(binding?.etEmail?.text) -> {
                showErrorSnackBar("Email must not be empty")
                false
            }
            TextUtils.isEmpty(binding?.etPassword?.text) -> {
                showErrorSnackBar("Password must not be empty")
                false
            }
            else -> {
                true
            }
        }
    }
}
package com.example.projemanag.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {
    var binding: ActivitySignUpBinding? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
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
        setSupportActionBar(binding?.toolbarSignUpActivity)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        binding?.toolbarSignUpActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnSignUp?.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        if(validateUser()) {
            val name = binding?.etName?.text
                val email = binding?.etEmail?.text
                    val password = binding?.etPassword?.text
        }
    }

    private fun validateUser(): Boolean {
        return when {
            TextUtils.isEmpty(binding?.etName?.text) -> {
                showErrorSnackBar("Name must not be empty")
                false
            }
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
package com.example.projemanag.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import com.example.projemanag.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    var binding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        binding?.btnSignUpIntro?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding?.btnSignInIntro?.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
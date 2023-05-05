package com.example.projemanag.activities

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.projemanag.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class BaseActivity : AppCompatActivity() {
    private var isBackPressedOnce: Boolean = false
    private var progressDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid;
    }

    fun showProgressDialog(text: String) {
        progressDialog = Dialog(this)
        progressDialog!!.setContentView(R.layout.dialog_progress)
        progressDialog!!.findViewById<TextView>(R.id.tv_progress_text).text = text
        progressDialog!!.show()
    }
    fun hideProgressDialog() {
        progressDialog?.let {
            it.hide()
        }
    }
    fun onBackCheckPressed() {
        if(isBackPressedOnce) {
            super.onBackPressed()
            return
        }
        this.isBackPressedOnce = true
        Toast.makeText(
            this,
            "Please click back again to exit",
            Toast.LENGTH_SHORT
        ).show()
        Handler().postDelayed({
            isBackPressedOnce = false
        },2000)
    }
    fun showErrorSnackBar(message: String) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            Color.parseColor("#FF0000")
        )
        snackBar.show()
    }
}
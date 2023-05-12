package com.example.projemanag.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivityMainBinding
import com.example.projemanag.databinding.NavHeaderMainBinding
import com.example.projemanag.firebase.FirestoreClass
import com.example.projemanag.models.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    var binding: ActivityMainBinding? = null
    var headerBinding: NavHeaderMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setupUI()
        binding!!.navView.setNavigationItemSelectedListener(this)


    }

    private fun setupUI() {
        val headerView: View = binding?.navView?.getHeaderView(0)!!
        headerBinding = NavHeaderMainBinding.bind(headerView)
        setSupportActionBar(binding!!.lAppBar.toolbar)
        binding!!.lAppBar.toolbar.setNavigationIcon(R.drawable.ic_menu)
        binding!!.lAppBar.toolbar.setNavigationOnClickListener {
            if(binding!!.drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                binding!!.drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                binding!!.drawerLayout!!.openDrawer(GravityCompat.START)
            }
        }
        FirestoreClass().signInUser(this@MainActivity)
    }
    fun loadUserNavigationData(user: User) {
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_user_place_holder)
            .into(headerBinding!!.ivProfile);
        headerBinding?.tvUsername?.text = user.name
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.drawer_profile -> {
                Toast.makeText(this@MainActivity,"Profile selected",Toast.LENGTH_SHORT).show()
            }
            R.id.drawer_signout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}
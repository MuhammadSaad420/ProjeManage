package com.example.projemanag.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setupUI()
        binding!!.navView.setNavigationItemSelectedListener(this)


    }

    private fun setupUI() {
        setSupportActionBar(binding!!.lAppBar.toolbar)
        binding!!.lAppBar.toolbar.setNavigationIcon(R.drawable.ic_menu)
        binding!!.lAppBar.toolbar.setNavigationOnClickListener {
            if(binding!!.drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                binding!!.drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                binding!!.drawerLayout!!.openDrawer(GravityCompat.START)

            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.drawer_profile -> {
                Toast.makeText(this@MainActivity,"Profile selected",Toast.LENGTH_SHORT).show()
            }
            R.id.drawer_signout -> {
                Toast.makeText(this@MainActivity,"Signout selected",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}
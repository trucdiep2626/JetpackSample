package com.example.samplejetpack.navigation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.samplejetpack.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationMainActivity : AppCompatActivity() {
    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_main)
       println(R.id.nav_host_fragment)

         controller= findNavController(R.id.nav_host_fragment)

        var botNav= findViewById<BottomNavigationView>(R.id.botNav)

        botNav.setupWithNavController(controller)
        var toolbar= findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.setupWithNavController(controller)
       setSupportActionBar(toolbar)
 //       tool
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(controller) || super.onOptionsItemSelected(item)
    }
}
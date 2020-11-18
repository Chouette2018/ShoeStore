package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.Utility.Companion.isSignedIn
import com.udacity.shoestore.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        setupStartPage()
        configureToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out ->{
                Utility.signOut(this)
                val startDestination = findNavController(R.id.my_nav_host_fragment).graph.startDestination
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(startDestination, true)
                    .build()
                findNavController(R.id.my_nav_host_fragment).navigate(R.id.loginFragment, null, navOptions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun configureToolbar(){
        val navController = findNavController(R.id.my_nav_host_fragment)
        //Avoid displaying back button and declaring top level destination
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.loginFragment, R.id.shoesFragment ).build()
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.toolbar)
    }

    private fun setupStartPage(){
        val navHostFragment = my_nav_host_fragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.main_nav)
        val navController = navHostFragment.navController

        val destination = if (isSignedIn(this)) R.id.shoesFragment  else R.id.loginFragment
        navGraph.startDestination = destination
        navController.graph = navGraph

        //val navHostFragment :NavHostFragment = binding.myNavHostFragment as NavHostFragment

    }
}

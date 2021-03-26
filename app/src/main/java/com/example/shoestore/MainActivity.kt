package com.example.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.databinding.ActivityMainBinding
import com.example.shoestore.login.data.LoginRepository
import com.example.shoestore.login.data.model.LoggedInUser
import com.example.shoestore.login.login.LoginViewModel
import com.example.shoestore.login.login.LoginViewModelFactory
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.loginFragment,
                R.id.welcomeFragment,
                R.id.shoeListFragment
            )
            .build()

        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.toolbar.title = resources.getString(R.string.app_name)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                val loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                    .get(LoginViewModel::class.java)
                loginViewModel.logout()
                findNavController(R.id.myNavHostFragment).navigate(R.id.loginFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }
}
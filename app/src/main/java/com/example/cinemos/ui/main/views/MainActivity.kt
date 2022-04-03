package com.example.cinemos.ui.main.views

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cinemos.R
import com.example.cinemos.databinding.MainActivityBinding

var isConnected:Boolean = false

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        isConnected = CheckConnectionFragment.newInstance().isOnline(this)
        if (!isConnected) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CheckConnectionFragment.newInstance()).commit()
        } else {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commit()
            }
            binding.bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.itemHome -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, HomeFragment())
                            .commit()
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.itemFavorite -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, FavoriteFragment())
                            .commit()
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.itemProfile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ProfileFragment())
                            .commit()
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
        }
    }
}




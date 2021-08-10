package com.example.cinemos.ui.main.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cinemos.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment,fragment.javaClass.getSimpleName()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.itemFavorite ->{
                    val fragment = FavoriteFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment,fragment.javaClass.getSimpleName()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.itemProfile->{
                    val fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment,fragment.javaClass.getSimpleName()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}



package com.example.cinemos.ui.main.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cinemos.R
import com.example.cinemos.ui.main.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val bottomNavigationView = view?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> { Toast.makeText(context,"Home page",Toast.LENGTH_SHORT).show()
return@setOnNavigationItemSelectedListener true
                }
                R.id.itemFavorite -> {Toast.makeText(context,"Favorite page",Toast.LENGTH_SHORT).show()
                return@setOnNavigationItemSelectedListener true}
                R.id.itemProfile -> {
                    Toast.makeText(context,"Profile page",Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}

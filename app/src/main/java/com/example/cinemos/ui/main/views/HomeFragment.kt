package com.example.cinemos.ui.main.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinemos.R
import com.example.cinemos.databinding.MainFragmentBinding
import com.example.cinemos.ui.main.adapters.MainFragmentAdapter
import com.example.cinemos.ui.main.model.FactDTO
import com.example.cinemos.ui.main.viewModel.AppState
import com.example.cinemos.ui.main.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movieData: FactDTO) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(InsideFragment.BUNDLE_EXTRA, movieData)
                manager.beginTransaction()
                    .add(R.id.container, InsideFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getDataFromLocalSource()
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.searchItem-> {searchItemClick()}
                R.id.filterItem -> {
                fragmentManager?.beginTransaction()?.replace(R.id.container,FilterFragment())?.commit()
                return@setOnMenuItemClickListener true
            }
            }
            false
        }

    }

    private fun searchItemClick() {
        Toast.makeText(context,"Открылся поиск",Toast.LENGTH_SHORT).show()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(appState.movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getMovie() }
                    .show()
            }
        }
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movieData: FactDTO)
    }
}

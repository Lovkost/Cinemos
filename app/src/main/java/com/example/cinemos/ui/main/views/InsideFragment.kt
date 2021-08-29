package com.example.cinemos.ui.main.views

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.cinemos.R
import com.example.cinemos.databinding.FragmentInsideBinding
import com.example.cinemos.ui.main.model.FactDTO
import com.example.cinemos.ui.main.model.MovieDTO
import com.example.cinemos.ui.main.model.MovieData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val API_KEY = "80532f6339bb492b1aa7aec6675487b2"

class InsideFragment : Fragment() {
    private var _binding: FragmentInsideBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieBundle: FactDTO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsideBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayMovie(movieBundle)
    }

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle): InsideFragment {
            val fragment = InsideFragment()
            fragment.arguments = bundle
            fragment.movieBundle = bundle.get(BUNDLE_EXTRA) as FactDTO
            return fragment
        }
    }

     private fun displayMovie(movieDTO: FactDTO) {
         val link = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
         val imageView = view?.findViewById<ImageView>(R.id.imageViewFragmentInside)
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            titleFragmentInside.text = movieDTO.original_title
                ratingFragmentInside.text = movieDTO.popularity
                budgetFragmentInside.text = movieDTO.budget
                descriptionFragmentInside.text = movieDTO.overview
            if (imageView != null) {
                context?.let { Glide.with(it).load("$link${movieDTO.poster_path}").into(imageView) }
            }

        }
    }
}

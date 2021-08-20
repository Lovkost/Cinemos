package com.example.cinemos.ui.main.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemos.R
import com.example.cinemos.databinding.FragmentInsideBinding
import com.example.cinemos.ui.main.model.MovieData

class InsideFragment : Fragment() {
    private var _binding: FragmentInsideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsideBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieData>(BUNDLE_EXTRA)?.let{ movie->
        movie.title.also {
            binding.titleFragmentInside.text = movie.title
            binding.ratingFragmentInside.text = movie.rating
            binding.budgetFragmentInside.text = movie.budget
            binding.descriptionFragmentInside.text = movie.description
        }}
    }

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle): InsideFragment {
            val fragment = InsideFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}

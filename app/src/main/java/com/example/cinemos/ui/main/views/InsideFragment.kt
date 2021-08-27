package com.example.cinemos.ui.main.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.cinemos.databinding.FragmentInsideBinding
import com.example.cinemos.ui.main.model.*

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_DATA_EMPTY_EXTRA = "DATA IS EMPTY"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "RESPONSE IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TITLE_EXTRA = "TEMPERATURE"
const val DETAILS_POPULARITY_EXTRA = "FEELS LIKE"
const val DETAILS_BUDGET_EXTRA = "CONDITION"
const val DETAILS_OVERVIEW_EXTRA = "OVERVIEW"
private const val PROCESS_ERROR = "Обработка ошибки"

class InsideFragment : Fragment() {

    private var _binding: FragmentInsideBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieBundle: FactDTO
    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
                    FactDTO(
                        intent.getStringExtra(DETAILS_TITLE_EXTRA),
                        intent.getStringExtra(DETAILS_POPULARITY_EXTRA),
                        intent.getStringExtra(DETAILS_BUDGET_EXTRA),
                        intent.getStringExtra(DETAILS_OVERVIEW_EXTRA)
                    )
                )
                else -> TODO(PROCESS_ERROR)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA)!!
        getWeather()
    }

    private fun getWeather() {
        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        context?.let {
            it.startService(Intent(it, DetailsService::class.java).apply {
                putExtra(
                    POPULAR,
                    "popularity"
                )
            })
        }
    }

    private fun renderData(weatherDTO: FactDTO) {
        binding.mainView.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE

        val title = weatherDTO.original_title
        val popularity = weatherDTO.popularity
        val budget = weatherDTO.budget
        val overview = weatherDTO.overview

        if (title == null || popularity == null || budget == null) {
            TODO(PROCESS_ERROR)
        } else {
            binding.titleFragmentInside.text = title
            binding.ratingFragmentInside.text = popularity
            binding.budgetFragmentInside.text = budget
            binding.descriptionFragmentInside.text = overview

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): InsideFragment {
            val fragment = InsideFragment()
            fragment.arguments = bundle
            fragment.movieBundle = bundle.get(BUNDLE_EXTRA) as FactDTO
            return fragment
        }
    }
}
/*
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
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE

            titleFragmentInside.text = movieDTO.original_title
                ratingFragmentInside.text = movieDTO.popularity
                budgetFragmentInside.text = movieDTO.budget
                descriptionFragmentInside.text = movieDTO.overview

        }
    }
}
*/

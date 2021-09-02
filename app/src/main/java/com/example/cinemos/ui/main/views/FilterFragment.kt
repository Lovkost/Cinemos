package com.example.cinemos.ui.main.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import com.example.cinemos.R
import kotlinx.android.synthetic.main.fragment_filter.*

 const val IS_ADULT_KEY = "ADULT_KEY"

class FilterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val flagAdult = view.findViewById<CheckBox>(R.id.flagAdult)
         flagAdult.isChecked = loadPref(IS_ADULT_KEY)
        flagAdult.setOnCheckedChangeListener { buttonView, isChecked ->
            saveFlagState(isChecked)
        }
    }

     fun loadPref(key:String): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            return sharedPref.getBoolean(key,false)
        } else return false
    }

    private fun saveFlagState(flag:Boolean) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putBoolean(IS_ADULT_KEY,flag)
        editor?.apply()
            }
        }


    private fun flagProcessing(flagAdult: CheckBox) {
        if (flagAdult.isChecked) {

        } else {
        }

    }

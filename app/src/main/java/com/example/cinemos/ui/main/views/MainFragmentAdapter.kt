package com.example.cinemos.ui.main.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemos.R
import com.example.cinemos.ui.main.model.MovieData

class MainFragmentAdapter :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieData: List<MovieData> = listOf()

    fun setMovie(data: List<MovieData>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_fragment_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movieData: MovieData) {
            val dataTitle = movieData.title
            if(dataTitle.length<=30) itemView.findViewById<TextView>(R.id.mainFragmentRecyclerViewTextView).text = dataTitle
            else itemView.findViewById<TextView>(R.id.mainFragmentRecyclerViewTextView).text = dataTitle.substring(0,30) + "..."
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    movieData.title,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}


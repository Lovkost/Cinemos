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

class MainFragmentAdapter(private var onItemViewClickListener: HomeFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieData: List<MovieData> = listOf()

    fun removeListener(){
        onItemViewClickListener = null
    }

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
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerViewTextView).text = movieData.title
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movieData)
            }
        }
    }
}


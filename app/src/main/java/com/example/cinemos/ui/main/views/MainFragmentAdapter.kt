package com.example.cinemos.ui.main.views

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemos.R
import com.example.cinemos.ui.main.model.FactDTO
import com.example.cinemos.ui.main.model.Model
import com.example.cinemos.ui.main.model.MovieDTO

class MainFragmentAdapter(private var onItemViewClickListener: HomeFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.N)
    private  var movieData: MovieDTO = Model().loadMovie()
    fun removeListener(){
        onItemViewClickListener = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setMovie(data: MovieDTO) {
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        movieData.results[position]?.let { holder.bind(it) }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getItemCount(): Int {
        return movieData.results.size
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movieData: FactDTO) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerViewTextView).text = movieData.original_title
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movieData)
            }
        }
    }
}


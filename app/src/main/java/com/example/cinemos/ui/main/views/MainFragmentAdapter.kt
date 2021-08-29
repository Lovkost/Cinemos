package com.example.cinemos.ui.main.views

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemos.R
import com.example.cinemos.ui.main.model.FactDTO
import com.example.cinemos.ui.main.model.Model
import com.example.cinemos.ui.main.model.MovieDTO

class MainFragmentAdapter(private var onItemViewClickListener: HomeFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.N)
    private  var movieData: MovieDTO = Model().loadMovie()
    lateinit var context: Context
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
         context = parent.context
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
            val link = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
             val imageView = itemView.findViewById<ImageView>(R.id.mainFragmentRecyclerView_image)
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerViewTextView).text = movieData.original_title
            Glide.with(context).load("$link${movieData.poster_path}").into(imageView)
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movieData)
            }
        }
    }
}


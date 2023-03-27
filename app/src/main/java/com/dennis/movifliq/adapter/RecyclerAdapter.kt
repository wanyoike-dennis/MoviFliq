package com.dennis.movifliq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dennis.movifliq.R
import com.dennis.movifliq.data.Movie

class RecyclerAdapter(private val movies:List<Movie>) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)  {
        val title:TextView = itemView.findViewById(R.id.txt_title)
        val year:TextView=itemView.findViewById(R.id.txt_year)
        val posterImage: ImageView = itemView.findViewById(R.id.img_poster)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view,parent,false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.year.text= movie.year
        Glide.with(holder.posterImage)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
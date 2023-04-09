package com.dennis.movifliq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dennis.movifliq.R
import com.dennis.movifliq.data.Movies

class MovieAdapter(private val onClick: (Movies) -> Unit) : ListAdapter<Movies, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return MovieViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

}

class MovieViewHolder(itemView:View, val onClick: (Movies) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private var currentMovie : Movies? = null
    private val imageView : ImageView = itemView.findViewById(R.id.img_poster)

    init {
        itemView.setOnClickListener {
            currentMovie?.let {
                onClick(it)
            }
        }
    }

    fun bind(movie: Movies) {
        currentMovie = movie
      //  binding.txtTitle.text = movie.title
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
    }

}

class MovieDiffCallback : DiffUtil.ItemCallback<Movies>() {

    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }

}

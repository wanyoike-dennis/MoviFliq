package com.dennis.movifliq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dennis.movifliq.data.Movies
import com.dennis.movifliq.databinding.ItemViewBinding

class MovieAdapter : ListAdapter<Movies, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

}

class MovieViewHolder(private val binding: ItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movies) {
        binding.txtTitle.text = movie.title


        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(binding.imgPoster)
            .load(imageUrl)

            .into(binding.imgPoster)
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

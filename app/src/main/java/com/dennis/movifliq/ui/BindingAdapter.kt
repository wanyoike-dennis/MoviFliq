package com.dennis.movifliq.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dennis.movifliq.R
import com.dennis.movifliq.viewmodels.MoviesApiStatus

@BindingAdapter("moviesApiStatus")
fun bindStatus(statusImageView: ImageView,status: MoviesApiStatus?){
    when (status){
        MoviesApiStatus.LOADING ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MoviesApiStatus.ERROR ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MoviesApiStatus.DONE ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_done_24)
        }
        else ->{}
    }
}

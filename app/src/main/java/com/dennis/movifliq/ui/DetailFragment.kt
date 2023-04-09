package com.dennis.movifliq.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dennis.movifliq.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private val args:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentDetailBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadData(){
       val imageUrl = args.imagePath
        binding?.apply {
            Glide.with(requireActivity())
                .load(imageUrl)
                .into(imgView)
            txtIdmbRating.text = args.rating.toString()
            txtRatingScale.text = args.voteCount.toString()
            txtDetailsTitle.text = args.title
            txtDetailDescription.text = args.overview
        }

    }
}
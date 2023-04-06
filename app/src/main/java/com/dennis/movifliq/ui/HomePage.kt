package com.dennis.movifliq.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dennis.movifliq.adapter.MovieAdapter
import com.dennis.movifliq.data.Movies
import com.dennis.movifliq.databinding.FragmentHomePageBinding
import com.dennis.movifliq.viewmodels.MoviesViewModel


class HomePage : Fragment() {

    private val viewModel: MoviesViewModel by activityViewModels()
    private var binding: FragmentHomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter()
        binding?.recycler?.adapter = adapter

        viewModel.moviesLiveData.observe(this.viewLifecycleOwner)
        { movies -> movies.let {
            adapter.submitList(it as MutableList<Movies>)
        } }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
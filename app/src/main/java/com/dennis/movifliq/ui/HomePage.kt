package com.dennis.movifliq.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        val adapter = MovieAdapter {
            movie -> onAdapterClick(movie)
        }
            val lm = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding?.recycler?.layoutManager = lm
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

    private fun onAdapterClick(movie:Movies){
        val tittle = movie.title
        val overview = movie.overview
        val posterPath = movie.posterPath
        val releaseDate= movie.releaseDate
        val voteAverage = movie.voteAverage
        val voteCount = movie.voteCount

        val action = HomePageDirections.actionHomePageToDetailFragment()
        findNavController().navigate(action)
    }


    }

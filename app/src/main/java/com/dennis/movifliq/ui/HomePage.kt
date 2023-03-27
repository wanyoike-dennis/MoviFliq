package com.dennis.movifliq.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.dennis.movifliq.adapter.RecyclerAdapter
import com.dennis.movifliq.databinding.FragmentHomePageBinding
import com.dennis.movifliq.viewmodels.SearchViewModel
import kotlinx.coroutines.*


class HomePage : Fragment() {

    private val searchViewModel : SearchViewModel by activityViewModels()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private  var binding: FragmentHomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomePageBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coroutineScope.launch {
            try {
                val movies =searchViewModel.searchMovies("iron man")
                binding?.recycler?.adapter = RecyclerAdapter(movies)

            }
            catch (e:Exception){
                Toast.makeText(requireActivity(),e.message,Toast.LENGTH_SHORT).show()
                println(e.message.toString())
            }



        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        coroutineScope.cancel()
    }
}
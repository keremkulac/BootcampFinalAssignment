package com.keremkulac.bootcampfinalassignment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.keremkulac.bootcampfinalassignment.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        setRecyclerView()
        return binding.root
    }


    private fun setRecyclerView(){
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodsAdapter(requireContext(),it)
            binding.foodsAdapter = adapter
        }
    }


}
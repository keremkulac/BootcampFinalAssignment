package com.keremkulac.bootcampfinalassignment.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
   private lateinit var binding : FragmentBasketBinding
   private val viewModel by viewModels<BasketViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket,container,false)
        get()
        return binding.root
    }

    fun get(){
        viewModel.getBasketItems("kerem")
        viewModel.basketItems.observe(viewLifecycleOwner){
            val adapter = BasketAdapter(requireContext(),it)
            binding.adapter = adapter

        }


    }

}
package com.keremkulac.bootcampfinalassignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        binding.homeObject = this
        setRecyclerView()
        setBasketIconSizeBadge()
        return binding.root
    }


    private fun setRecyclerView(){
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodsAdapter(requireContext(),it)
            binding.foodsAdapter = adapter
        }
    }

    private fun setBasketIconSizeBadge(){
        viewModel.basketItemsSize.observe(viewLifecycleOwner){
            binding.basketIcon.badgeValue = it
        }
    }

    fun goToBasket(){
        Navigation.findNavController(binding.root).navigate(HomeFragmentDirections.actionHomeFragmentToBasketFragment())
    }

}
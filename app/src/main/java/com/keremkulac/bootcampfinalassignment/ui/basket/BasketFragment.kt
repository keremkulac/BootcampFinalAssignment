package com.keremkulac.bootcampfinalassignment.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
   private lateinit var binding : FragmentBasketBinding
   private lateinit var adapter : BasketAdapter
   private val viewModel by viewModels<BasketViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket,container,false)
        binding.basketObject = this
        observeEmptyList()
        observeBasketItemList()
        calculate()
        return binding.root
    }

    private fun observeBasketItemList(){
        viewModel.basketItems.observe(viewLifecycleOwner) {list->
            if(list.isNotEmpty()){
                adapter = BasketAdapter(requireContext())
                adapter.basketItemsList = ArrayList(list)
                binding.adapter = adapter
                binding.totalPrice.text = viewModel.calculateTotalPrice(list).toString()
                increaseClick()
                decreaseClick()
            }
        }
    }

    private fun observeEmptyList(){
        viewModel.error.observe(viewLifecycleOwner){
            if (it){
                binding.emptyListWarning.visibility = View.VISIBLE
            }else{
                binding.emptyListWarning.visibility = View.GONE
            }
        }
    }


    private fun calculate(){
        viewModel.basketItems.observe(viewLifecycleOwner){
            val total = viewModel.calculateTotalPrice(it)
            binding.totalPrice.text = "$total ₺"
        }
    }

    fun back(){
        Navigation.findNavController(binding.root).navigate(BasketFragmentDirections.actionBasketFragmentToHomeFragment())
    }

    private fun increaseClick(){
        adapter.increaseClickListener = {basketItems, itemPiece ->
            viewModel.increaseBasketItem(itemPiece,basketItems)
            Navigation.findNavController(binding.root).navigate(R.id.basketFragment)

        }
    }

    private fun decreaseClick(){
        adapter.decreaseClickListener = {basketItems, itemPiece ->
            viewModel.decreaseBasketItem(itemPiece,basketItems)
            Navigation.findNavController(binding.root).navigate(R.id.basketFragment)
        }
    }
}
package com.keremkulac.bootcampfinalassignment.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FragmentFoodDetailBinding
import com.keremkulac.bootcampfinalassignment.entity.Foods
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding : FragmentFoodDetailBinding
    private val viewModel by viewModels<FoodDetailViewModel>()
    private var food : Foods? = null
    private val arg : FoodDetailFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail,container,false)
        food = arg.food
        binding.detailObject = this
        binding.food = food
        setBasketIconSizeBadge()
        checkAndSet()
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food!!.foodPicture}"
        Glide.with(requireContext()).load(url).override(500,700).into(binding.foodImage)
        return binding.root
    }


     fun increasePiece(){
        binding.increase.setOnClickListener {
            var piece = binding.piece.text.toString().toInt()
            piece++
            binding.decrease.isClickable = true
            binding.decrease.isFocusable = true
            binding.piece.text = piece.toString()
            binding.decrease.setImageDrawable(setDrawable(R.color.sub_main))
        }
    }

    fun decreasePiece(){
        binding.decrease.setOnClickListener {
            var piece = binding.piece.text.toString().toInt()
            piece--
            binding.piece.text = piece.toString()
            if(piece == 1){
                binding.decrease.isClickable = false
                binding.decrease.isFocusable = false
                binding.decrease.setImageDrawable(setDrawable(R.color.grey))
            }
        }
    }

     fun insertBasket(){
        food?.let { food->
            viewModel.error.observe(viewLifecycleOwner){isEmpty->
                if(isEmpty){
                    viewModel.checkBasketItems(isEmpty,listOf(),food,binding.piece.text.toString().toInt())
                }else{
                    viewModel.basketItems.observe(viewLifecycleOwner){list->
                        viewModel.checkBasketItems(isEmpty,list,food,binding.piece.text.toString().toInt())
                    }
                }
            }

        }
    }

    private fun setBasketIconSizeBadge(){
        viewModel.basketItems.observe(viewLifecycleOwner){
            binding.basketIcon.badgeValue = it.size
        }
    }
    fun goToBasket(){
        Navigation.findNavController(binding.root).navigate(FoodDetailFragmentDirections.actionFoodDetailFragmentToBasketFragment())
    }

    fun checkAndSet(){
        food?.let {comingFood->
            viewModel.basketItems.observe(viewLifecycleOwner){
                val same = it.find { it.foodName == comingFood.foodName }
                    if( same != null){
                        binding.piece.text = same.foodPiece.toString()
                    }else{
                        binding.decrease.setImageDrawable(setDrawable(R.color.grey))
                    }
            }
        }
    }

    private fun setDrawable(colorID : Int) : Drawable?{
        val originalDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.ic_decrease_round)
        val drawable = originalDrawable?.constantState?.newDrawable()
        val newColor = ContextCompat.getColor(requireContext(),colorID)
        if (drawable != null) {
            drawable.mutate()
            DrawableCompat.setTint(drawable, newColor)
        }
        return drawable
    }
}
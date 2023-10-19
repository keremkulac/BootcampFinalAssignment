package com.keremkulac.bootcampfinalassignment.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FragmentFoodDetailBinding
import com.keremkulac.bootcampfinalassignment.entity.Foods
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding : FragmentFoodDetailBinding
    private var food : Foods? = null
    private val arg : FoodDetailFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail,container,false)
        food = arg.food
        binding.food = food
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food!!.foodPicture}"
        Glide.with(requireContext()).load(url).override(500,700).into(binding.foodImage)
        increasePiece()
        decreasePiece()
        return binding.root
    }


    private fun increasePiece(){
        binding.increase.setOnClickListener {
            var piece = binding.piece.text.toString().toInt()
            piece++
            binding.decrease.isClickable = true
            binding.decrease.isFocusable = true
            binding.piece.text = piece.toString()
            val originalDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.ic_decrease_round)
            val drawable = originalDrawable?.constantState?.newDrawable()
            val newColor = ContextCompat.getColor(requireContext(), R.color.purple_500)
            if (drawable != null) {
                  drawable.mutate()
                  DrawableCompat.setTint(drawable, newColor)
            }
            binding.decrease.setImageDrawable(drawable)
        }
    }

    private fun decreasePiece(){
        binding.decrease.setOnClickListener {
            var piece = binding.piece.text.toString().toInt()
            piece--
            binding.piece.text = piece.toString()
            if(piece == 1){
                binding.decrease.isClickable = false
                binding.decrease.isFocusable = false
                val originalDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.ic_decrease_round)
                val drawable = originalDrawable?.constantState?.newDrawable()
                val newColor = ContextCompat.getColor(requireContext(), R.color.grey)
                if (drawable != null) {
                    drawable.mutate()
                    DrawableCompat.setTint(drawable, newColor)
                }
                binding.decrease.setImageDrawable(drawable)
            }
        }
    }

}
package com.keremkulac.bootcampfinalassignment.ui.addbasket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FragmentAddBasketBinding
import com.keremkulac.bootcampfinalassignment.entity.Foods
import com.keremkulac.bootcampfinalassignment.ui.detail.FoodDetailFragmentArgs


class AddBasketFragment : BottomSheetDialogFragment() {

    private lateinit var  binding : FragmentAddBasketBinding
    private var food : Foods? = null
    private val arg : FoodDetailFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_basket,container,false)

        food = arg.food
        binding.food = food
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food!!.foodPicture}"
        Glide.with(requireContext()).load(url).override(500,700).into(binding.foodImage)
        return binding.root
    }


}
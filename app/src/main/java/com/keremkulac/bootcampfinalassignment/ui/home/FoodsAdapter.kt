package com.keremkulac.bootcampfinalassignment.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.FoodItemBinding
import com.keremkulac.bootcampfinalassignment.entity.Foods

class FoodsAdapter(val context : Context,val list : List<Foods>) : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>(){

    inner class FoodsViewHolder(val binding : FoodItemBinding ) : RecyclerView.ViewHolder(binding.root){}

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = list[position]
        holder.binding.food = food
        holder.binding.foodExplanation.text = context.getString(R.string.explanation)
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.foodPicture}"
        Glide.with(context).load(url).override(500,700).into(holder.binding.foodImage)
        holder.binding.foodItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFoodDetailFragment(food)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding : FoodItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.food_item,parent,false)
        return FoodsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
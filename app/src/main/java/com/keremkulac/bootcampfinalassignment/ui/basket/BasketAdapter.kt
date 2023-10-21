package com.keremkulac.bootcampfinalassignment.ui.basket

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.BasketItemBinding
import com.keremkulac.bootcampfinalassignment.entity.BasketItems

class BasketAdapter(val context: Context,val basketItemsList : List<BasketItems>) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    inner class BasketViewHolder(val binding : BasketItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItems = basketItemsList[position]
        holder.binding.basketItem = basketItems
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basketItems.foodPicture}"
        Glide.with(context).load(url).override(500,700).into(holder.binding.foodImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding : BasketItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.basket_item,parent,false)
        return BasketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return basketItemsList.size
    }

}
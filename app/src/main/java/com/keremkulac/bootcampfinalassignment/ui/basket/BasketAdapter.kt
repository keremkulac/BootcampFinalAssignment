package com.keremkulac.bootcampfinalassignment.ui.basket

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keremkulac.bootcampfinalassignment.R
import com.keremkulac.bootcampfinalassignment.databinding.BasketItemBinding
import com.keremkulac.bootcampfinalassignment.entity.BasketItems

class BasketAdapter(val context: Context) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    inner class BasketViewHolder(val binding : BasketItemBinding) : RecyclerView.ViewHolder(binding.root){}

    var increaseClickListener: ((BasketItems,Int) -> Unit)? = null
    var decreaseClickListener: ((BasketItems,Int) -> Unit)? = null

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItems = basketItemsList[position]
        holder.binding.basketItem = basketItems
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basketItems.foodPicture}"
        Glide.with(context).load(url).override(500,700).into(holder.binding.foodImage)
        holder.binding.itemPiece.text = basketItems.foodPiece.toString()
        holder.binding.increaseItem.setOnClickListener {
            increaseClickListener?.invoke(basketItems,holder.binding.itemPiece.text.toString().toInt())
        }
        holder.binding.decreaseItem.setOnClickListener {
            decreaseClickListener?.invoke(basketItems,holder.binding.itemPiece.text.toString().toInt())
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding : BasketItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.basket_item,parent,false)
        return BasketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return basketItemsList.size
    }


    private val diffUtil = object : DiffUtil.ItemCallback<BasketItems>(){
        override fun areItemsTheSame(oldItem: BasketItems, newItem: BasketItems): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BasketItems, newItem: BasketItems): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var basketItemsList : ArrayList<BasketItems>
        get() = recyclerListDiffer.currentList.toMutableList() as ArrayList<BasketItems>
        set(value)  {
            recyclerListDiffer.submitList(value)
            notifyDataSetChanged()
        }


}
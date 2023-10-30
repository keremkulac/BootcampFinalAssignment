package com.keremkulac.bootcampfinalassignment.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepositoryImp
import com.keremkulac.bootcampfinalassignment.entity.BasketItems
import com.keremkulac.bootcampfinalassignment.entity.Foods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val foodsRepositoryImp: FoodsRepositoryImp) : ViewModel() {
    init {
        getBasketItems("kerem")
    }

    private val _basketItems = MutableLiveData<List<BasketItems>>()
    val basketItems: LiveData<List<BasketItems>>
        get() = _basketItems

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun getBasketItems(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _basketItems.postValue(foodsRepositoryImp.getBasketItems(userName).basketItems)
                _error.postValue(false)
            }catch (e : java.lang.Exception){
                _error.postValue(true)
            }
        }
    }

    private fun deleteBasketItem(basketItemID : Int){
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepositoryImp.deleteBasketItem(basketItemID,"kerem")
        }
    }

   private fun insertBasket(foodName : String
                    ,foodPicture : String
                    ,foodPrice : Int
                    ,foodPiece : Int
                    ,userName : String){
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepositoryImp.insertBasket(foodName,foodPicture,foodPrice,foodPiece,userName)
        }
    }

    fun checkBasketItems(isEmpty : Boolean,basketItemList : List<BasketItems>,food : Foods,piece : Int){
        if (!isEmpty) {
            val sameFood = basketItemList.find { it.foodName == food.foodName }
            if (sameFood != null) {
                deleteBasketItem(sameFood.foodID)
                insertBasket(food.foodName, food.foodPicture, food.foodPrice, piece, "kerem")
            } else {
                insertBasket(food.foodName, food.foodPicture, food.foodPrice, piece, "kerem")
            }
        } else {
            insertBasket(food.foodName, food.foodPicture, food.foodPrice, piece, "kerem")
        }
    }



}
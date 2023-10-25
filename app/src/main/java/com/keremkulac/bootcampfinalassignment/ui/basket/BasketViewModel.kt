package com.keremkulac.bootcampfinalassignment.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepositoryImp
import com.keremkulac.bootcampfinalassignment.entity.BasketItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val foodsRepositoryImp: FoodsRepositoryImp) : ViewModel()  {
    private val _basketItems = MutableLiveData<List<BasketItems>>()
    val basketItems: LiveData<List<BasketItems>>
        get() = _basketItems

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    init {
        getBasketItems("kerem")
    }
     fun getBasketItems(userName : String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                  _basketItems.postValue(foodsRepositoryImp.getBasketItems(userName).basketItems)
                _error.postValue(false)
            }catch (e : java.lang.Exception){
                _error.postValue(true)
            }
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

    fun calculateTotalPrice(basketList : List<BasketItems>) : Int{
        var total = 0
        for(basketListItem in basketList){
            total += basketListItem.foodPrice * basketListItem.foodPiece
        }
        return total
    }

    private fun deleteBasketItem(basketItemID : Int){
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepositoryImp.deleteBasketItem(basketItemID,"kerem")
        }
    }

    fun decreaseBasketItem(piece : Int,basketItems: BasketItems){
        var currentPiece = piece
        currentPiece--
        if(currentPiece>0){
            deleteBasketItem(basketItems.foodID)
            insertBasket(basketItems.foodName
                ,basketItems.foodPicture
                ,basketItems.foodPrice
                ,currentPiece
                , "kerem")
        }else{
            deleteBasketItem(basketItems.foodID)
        }
    }

    fun increaseBasketItem(piece : Int,basketItems: BasketItems){
        var currentPiece = piece
        currentPiece++
        deleteBasketItem(basketItems.foodID)
        insertBasket(basketItems.foodName
            ,basketItems.foodPicture
            ,basketItems.foodPrice
            ,currentPiece
            , "kerem")
    }



}
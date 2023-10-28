package com.keremkulac.bootcampfinalassignment.ui.basket

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepositoryImp
import com.keremkulac.bootcampfinalassignment.entity.BasketItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
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
                val sortedList = foodsRepositoryImp.getBasketItems(userName).basketItems.sortedByDescending { it.foodID }
                  _basketItems.postValue(sortedList)
                _error.postValue(false)
            }catch (e : java.lang.Exception){
                _error.postValue(true)
            }
        }
    }

    private fun insertBasket(foodName : String,foodPicture : String,foodPrice : Int,foodPiece : Int,userName : String){
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

    private fun deleteBasketItem(basketItemID : Int) : Int{
        var response = 0
        CoroutineScope(Dispatchers.Main).launch {
            response = foodsRepositoryImp.deleteBasketItem(basketItemID,"kerem").success
        }
        return response
    }

    fun decreaseBasketItem(basketItems: BasketItems,progressBar: ProgressBar,view: View){
        var currentPiece = basketItems.foodPiece.toString().toInt()
        currentPiece--
        beforeProcess(progressBar,view)
        GlobalScope.launch {
            if(currentPiece>0){
                foodsRepositoryImp.deleteBasketItem(basketItems.foodID,"kerem").success
            withContext(Dispatchers.Main){
                insertBasket(basketItems.foodName,basketItems.foodPicture,basketItems.foodPrice,currentPiece, "kerem")
                delay(1500)
                afterProcess(progressBar,view)
            }
            }else{
                deleteBasketItem(basketItems.foodID)
                afterProcess(progressBar,view)
            }
        }
    }

    fun increaseBasketItem(basketItems: BasketItems, progressBar: ProgressBar, view: View){
        var currentPiece = basketItems.foodPiece.toString().toInt()
        currentPiece++
        beforeProcess(progressBar,view)
        GlobalScope.launch {
            foodsRepositoryImp.deleteBasketItem(basketItems.foodID,"kerem")
            withContext(Dispatchers.Main){
                insertBasket(basketItems.foodName,basketItems.foodPicture,basketItems.foodPrice,currentPiece, "kerem")
                delay(1500)
                afterProcess(progressBar,view)
            }
        }
    }

    private fun beforeProcess(progressBar: ProgressBar,view: View){
        progressBar.visibility = View.VISIBLE
        view.isFocusable = false
        view.isClickable = false
    }

    private fun afterProcess(progressBar: ProgressBar,view: View){
        progressBar.visibility = View.GONE
        view.isFocusable = true
        view.isClickable = true
        getBasketItems("kerem")
    }

}
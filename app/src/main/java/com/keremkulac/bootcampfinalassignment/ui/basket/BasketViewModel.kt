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


         fun getBasketItems(userName : String){
            CoroutineScope(Dispatchers.Main).launch {
                _basketItems.postValue(foodsRepositoryImp.getBasketItems(userName))
            }
    }
}
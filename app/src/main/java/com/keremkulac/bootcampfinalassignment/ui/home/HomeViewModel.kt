package com.keremkulac.bootcampfinalassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepositoryImp
import com.keremkulac.bootcampfinalassignment.entity.Foods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val foodsRepositoryImp: FoodsRepositoryImp) : ViewModel() {
    private val _foodsList = MutableLiveData<List<Foods>>()
    val foodsList: LiveData<List<Foods>>
        get() = _foodsList

    private val _basketItemsSize = MutableLiveData<Int>()
    val basketItemsSize: LiveData<Int>
        get() = _basketItemsSize
    init {
        loadFoods()
        getBasketItemsSize()
    }

    private fun loadFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            _foodsList.postValue(foodsRepositoryImp.getAllFoods())
        }
    }

   private fun getBasketItemsSize(){
        CoroutineScope(Dispatchers.Main).launch {
            _basketItemsSize.postValue(foodsRepositoryImp.getBasketItems("kerem").basketItems.size)
        }
    }
}
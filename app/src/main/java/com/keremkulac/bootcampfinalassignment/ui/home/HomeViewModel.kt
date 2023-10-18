package com.keremkulac.bootcampfinalassignment.ui.home

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
class HomeViewModel @Inject constructor(val foodsRepositoryImp: FoodsRepositoryImp) : ViewModel() {
    val foodsList = MutableLiveData<List<Foods>>()

    init {
        loadFoods()
    }

    private fun loadFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodsList.value = foodsRepositoryImp.getAllFoods()
        }
    }
}
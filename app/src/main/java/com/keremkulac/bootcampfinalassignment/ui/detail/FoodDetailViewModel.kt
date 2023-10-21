package com.keremkulac.bootcampfinalassignment.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(val foodsRepositoryImp: FoodsRepositoryImp) : ViewModel() {

    fun insertBasket(foodName : String
                    ,foodPicture : String
                    ,foodPrice : Int
                    ,foodPiece : Int
                    ,userName : String){
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepositoryImp.insertBasket(foodName,foodPicture,foodPrice,foodPiece,userName)
            Log.d("TAG123",foodsRepositoryImp.insertBasket(foodName,foodPicture,foodPrice,foodPiece,userName).message)
        }
    }
}
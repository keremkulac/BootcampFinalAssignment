package com.keremkulac.bootcampfinalassignment.data.repository

import com.keremkulac.bootcampfinalassignment.data.api.FoodsApiService
import com.keremkulac.bootcampfinalassignment.entity.BasketItems
import com.keremkulac.bootcampfinalassignment.entity.Foods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsRepository(private val foodsApiService: FoodsApiService) {
    suspend fun getALlFoods() : List<Foods> =
        withContext(Dispatchers.IO){
            return@withContext foodsApiService.getAllFoods().foods
        }

    suspend fun insertBasket(foodName : String
                             ,foodPicture : String
                             ,foodPrice : Int
                             ,foodPiece : Int
                             ,userName : String) = foodsApiService.insertBasket(foodName,foodPicture,foodPrice,foodPiece,userName)
    suspend fun getBasketItems(userName: String) : List<BasketItems> =
        withContext(Dispatchers.IO){
            return@withContext foodsApiService.getBasketItems(userName).basketItems
        }
}

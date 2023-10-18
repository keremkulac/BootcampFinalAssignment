package com.keremkulac.bootcampfinalassignment.data.api

import com.keremkulac.bootcampfinalassignment.entity.FoodResponse
import retrofit2.http.GET

interface FoodsApiService {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods() : FoodResponse
}
package com.keremkulac.bootcampfinalassignment.data.api

import com.keremkulac.bootcampfinalassignment.entity.BasketResponse
import com.keremkulac.bootcampfinalassignment.entity.CRUDResponse
import com.keremkulac.bootcampfinalassignment.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsApiService {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods() : FoodResponse


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun insertBasket(@Field("yemek_adi") foodName : String,
                             @Field("yemek_resim_adi") foodPicture : String,
                             @Field("yemek_fiyat") foodPrice : Int,
                             @Field("yemek_siparis_adet") foodPiece : Int,
                             @Field("kullanici_adi") userName : String) : CRUDResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getBasketItems(@Field("kullanici_adi") userName: String) : BasketResponse

}
package com.keremkulac.bootcampfinalassignment.entity

import com.google.gson.annotations.SerializedName

data class BasketResponse (
    @SerializedName("sepet_yemekler") val basketItems : List<BasketItems>,
    @SerializedName("success") val success : Int
    )
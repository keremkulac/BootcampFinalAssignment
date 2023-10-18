package com.keremkulac.bootcampfinalassignment.entity

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("yemekler") val foods : List<Foods>,
    @SerializedName("success") val success : Int
    )

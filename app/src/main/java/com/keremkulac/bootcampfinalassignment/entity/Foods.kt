package com.keremkulac.bootcampfinalassignment.entity

import com.google.gson.annotations.SerializedName


data class Foods(
    @SerializedName("yemek_adi") val foodName : String,
    @SerializedName("yemek_id") val foodID : String,
    @SerializedName("yemek_resim_adi") val foodPicture : String,
    @SerializedName("yemek_fiyat") val foodPrice : String,

    ) : java.io.Serializable

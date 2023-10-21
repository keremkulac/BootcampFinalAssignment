package com.keremkulac.bootcampfinalassignment.entity

import com.google.gson.annotations.SerializedName

data class BasketItems (
    @SerializedName("sepet_yemek_id") val foodID : Int,
    @SerializedName("yemek_adi") val foodName : String,
    @SerializedName("yemek_resim_adi") val foodPicture : String,
    @SerializedName("yemek_fiyat") val foodPrice : Int,
    @SerializedName("yemek_siparis_adet") val foodPiece : Int,
    @SerializedName("kullanici_adi") val userName : String
    ) : java.io.Serializable
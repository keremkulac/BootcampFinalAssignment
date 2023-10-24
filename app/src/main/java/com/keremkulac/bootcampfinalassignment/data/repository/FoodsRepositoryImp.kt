package com.keremkulac.bootcampfinalassignment.data.repository

class FoodsRepositoryImp(val repository: FoodsRepository) {

    suspend fun getAllFoods() = repository.getALlFoods()

    suspend fun  insertBasket(foodName : String
                              ,foodPicture : String
                              ,foodPrice : Int
                              ,foodPiece : Int
                              ,userName : String) = repository.insertBasket(foodName,foodPicture,foodPrice,foodPiece,userName)
    suspend fun getBasketItems(userName: String) = repository.getBasketItems(userName)

    suspend fun deleteBasketItem(basketItemID : Int,userName : String) = repository.deleteBasketItem(basketItemID,userName)

}
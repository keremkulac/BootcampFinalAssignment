package com.keremkulac.bootcampfinalassignment.data.repository

class FoodsRepositoryImp(val repository: FoodsRepository) {

    suspend fun getAllFoods() = repository.getALlFoods()
}
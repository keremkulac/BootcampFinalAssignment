package com.keremkulac.bootcampfinalassignment.di

import com.keremkulac.bootcampfinalassignment.data.api.FoodsApiService
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepository
import com.keremkulac.bootcampfinalassignment.data.repository.FoodsRepositoryImp
import com.keremkulac.bootcampfinalassignment.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideFoodsRepository() : FoodsRepository {
        return  FoodsRepository(provideRetrofitInstance(BASE_URL).create(FoodsApiService::class.java))
    }
    @Provides
    @Singleton
    fun provideFoodsRepositoryImp(foodsRepository: FoodsRepository) : FoodsRepositoryImp {
        return FoodsRepositoryImp(foodsRepository)
    }

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL : String) : Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}


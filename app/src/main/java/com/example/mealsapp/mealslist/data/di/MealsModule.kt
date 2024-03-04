package com.example.mealsapp.mealslist.data.di

import com.example.mealsapp.mealslist.data.api.MealsAPI
import com.example.mealsapp.mealslist.data.api.MealsRepositoryImp
import com.example.mealsapp.mealslist.data.api.MealsServices
import com.example.mealsapp.mealslist.domain.MealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class MealsModule {

    @Provides
    fun getCategoryList(retrofit: Retrofit): MealsAPI = retrofit.create(MealsAPI::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCategoryRepository(service: MealsServices): MealsRepository =
        MealsRepositoryImp(service)
}
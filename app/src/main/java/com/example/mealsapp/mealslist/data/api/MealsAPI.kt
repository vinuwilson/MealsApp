package com.example.mealsapp.mealslist.data.api

import com.example.mealsapp.mealslist.data.model.Categories
import retrofit2.http.GET

interface MealsAPI {

    @GET("categories.php")
    suspend fun fetchAllCategoryList(): Categories
}
package com.example.mealsapp.data.api

import com.example.mealsapp.data.model.Categories
import retrofit2.http.GET

interface MealsAPI {

    @GET("categories.php")
    suspend fun fetchAllCategoryList(): Categories
}
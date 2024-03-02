package com.example.mealsapp.data.api

import com.example.mealsapp.data.model.Categories

interface MealsAPI {


    suspend fun fetchAllCategoryList(): Categories
}
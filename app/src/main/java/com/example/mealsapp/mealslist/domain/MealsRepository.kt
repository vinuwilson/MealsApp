package com.example.mealsapp.mealslist.domain

import com.example.mealsapp.mealslist.data.model.Categories
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    suspend fun getCategoryList(): Flow<Result<Categories>>
}

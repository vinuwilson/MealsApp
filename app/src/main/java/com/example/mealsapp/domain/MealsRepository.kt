package com.example.mealsapp.domain

import com.example.mealsapp.data.model.Categories
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    suspend fun getCategoryList(): Flow<Result<Categories>>
}

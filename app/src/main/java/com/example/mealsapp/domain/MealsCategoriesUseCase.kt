package com.example.mealsapp.domain

import com.example.mealsapp.data.model.Categories
import kotlinx.coroutines.flow.Flow

class MealsCategoriesUseCase(
    private val repository : MealsRepository
) {

    suspend fun getCategoryList(): Flow<Result<Categories>> {
        return repository.getCategoryList()
    }
}
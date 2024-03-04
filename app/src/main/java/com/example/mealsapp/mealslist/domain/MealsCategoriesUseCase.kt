package com.example.mealsapp.mealslist.domain

import com.example.mealsapp.mealslist.data.model.Categories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealsCategoriesUseCase @Inject constructor(
    private val repository: MealsRepository
) {

    suspend fun getCategoryList(): Flow<Result<Categories>> {
        return repository.getCategoryList()
    }
}
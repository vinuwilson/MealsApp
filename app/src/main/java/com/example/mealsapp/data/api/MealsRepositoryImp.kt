package com.example.mealsapp.data.api

import com.example.mealsapp.data.model.Categories
import com.example.mealsapp.domain.MealsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealsRepositoryImp @Inject constructor(
    private val services: MealsServices
) : MealsRepository {
    override suspend fun getCategoryList(): Flow<Result<Categories>> {
        return services.getCategoryList()
    }
}
package com.example.mealsapp.data.api

import com.example.mealsapp.data.model.Categories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MealsServices(
    private val api: MealsAPI
) {
    suspend fun getCategoryList(): Flow<Result<Categories>> {
        return flow {
            emit(Result.success(api.fetchAllCategoryList()))
        }.catch {
          emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}